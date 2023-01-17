package com.tutorial.flows;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import com.tutorial.contracts.CourseSubscriptionContract;
import com.tutorial.states.CourseState;
import com.tutorial.states.CourseSubscriptionState;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.contracts.LinearPointer;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.flows.CollectSignaturesFlow;
import net.corda.core.flows.FinalityFlow;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.FlowSession;
import net.corda.core.flows.InitiatedBy;
import net.corda.core.flows.InitiatingFlow;
import net.corda.core.flows.ReceiveFinalityFlow;
import net.corda.core.flows.SignTransactionFlow;
import net.corda.core.flows.StartableByRPC;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;

public class SubscribeToCourseFlow {
	@InitiatingFlow
	@StartableByRPC
	public static class SubscribeToCourseFlowInitiator extends FlowLogic<SignedTransaction> {
		Party subscriber;
		UniqueIdentifier courseId;

		public SubscribeToCourseFlowInitiator(Party subscriber, UniqueIdentifier courseId) {
			this.subscriber = subscriber;
			this.courseId = courseId;
		}

		@Override
		@Suspendable
		public SignedTransaction call() throws FlowException, NullPointerException {
			final Instant instantNow = Instant.now();

			final Party notary = getServiceHub().getNetworkMapCache()
					.getNotary(CordaX500Name.parse("O=Notary,L=London,C=GB"));

			List<StateAndRef<CourseState>> courseStateRefList = getServiceHub().getVaultService().queryBy(CourseState.class)
					.getStates();

			if (courseStateRefList.isEmpty()) {
				throw new FlowException("No Course Avaliable");
			}
			if (!courseStateRefList.stream().anyMatch(state -> state.getState().getData().getLinearId().equals(courseId))) {
				throw new FlowException("Course Not Found");
			}
			
			CourseSubscriptionState courseSubscriptionState = new CourseSubscriptionState(subscriber, getOurIdentity(),
					new UniqueIdentifier(), new LinearPointer<>(courseId, CourseState.class), instantNow, instantNow.plus(30,
							ChronoUnit.SECONDS), true);

			TransactionBuilder txBuilder = new TransactionBuilder(notary).addOutputState(courseSubscriptionState).addCommand(
					new CourseSubscriptionContract.Commands.Issue(),
					Arrays.asList(getOurIdentity().getOwningKey(), subscriber.getOwningKey()));

			txBuilder.verify(getServiceHub());
			// Sign the transaction.
			final SignedTransaction partSignedTx = getServiceHub().signInitialTransaction(txBuilder);

			// Send the state to the counterparty, and receive it back with their signature.
			FlowSession subscriberSession = initiateFlow(subscriber);

			final SignedTransaction fullySignedTx = subFlow(
					new CollectSignaturesFlow(partSignedTx, Arrays.asList(subscriberSession)));

			// Notarise and record the transaction in both parties' vaults.
			return subFlow(new FinalityFlow(fullySignedTx, Arrays.asList(subscriberSession)));
		}
	}

	@InitiatedBy(SubscribeToCourseFlowInitiator.class)
	public static class SubscribeToCourseFlowResponder extends FlowLogic<Void> {

		// private variable
		private FlowSession counterpartySession;

		public SubscribeToCourseFlowResponder(FlowSession counterpartySession) {
			this.counterpartySession = counterpartySession;
		}

		@Override
		@Suspendable
		public Void call() throws FlowException {
			SignedTransaction signedTransaction = subFlow(new SignTransactionFlow(counterpartySession) {
				@Override
				@Suspendable
				protected void checkTransaction(SignedTransaction stx) throws FlowException {
				}
			});

			// Stored the transaction into data base.
			subFlow(new ReceiveFinalityFlow(counterpartySession, signedTransaction.getId()));
			return null;
		}
	}
}
