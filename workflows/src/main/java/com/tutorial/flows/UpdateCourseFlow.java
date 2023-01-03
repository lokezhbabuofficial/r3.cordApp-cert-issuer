package com.tutorial.flows;

import java.util.Arrays;
import java.util.UUID;

import com.tutorial.contracts.CourseCertificationContractV2;
import com.tutorial.states.CourseState;

import co.paralleluniverse.fibers.Suspendable;
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
import net.corda.core.node.services.Vault.RelevancyStatus;
import net.corda.core.node.services.Vault.StateStatus;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;

public class UpdateCourseFlow {
	@InitiatingFlow
	@StartableByRPC
	public static class UpdateCourseFlowInitator extends FlowLogic<SignedTransaction> {
		private Party examinar;
		private UniqueIdentifier courseId;
		private String courseDec;
		private int coursePassScore;

		public UpdateCourseFlowInitator(Party examinar, UniqueIdentifier courseId, String courseDec, int coursePassScore) {
			this.examinar = examinar;
			this.courseId = courseId;
			this.courseDec = courseDec;
			this.coursePassScore = coursePassScore;
		}

		@Override
		@Suspendable
		public SignedTransaction call() throws FlowException {
			final Party notary = getServiceHub().getNetworkMapCache()
					.getNotary(CordaX500Name.parse("O=Notary,L=London,C=GB"));

			QueryCriteria.LinearStateQueryCriteria inputCriteria = new QueryCriteria.LinearStateQueryCriteria()
					.withUuid(Arrays.asList(UUID.fromString(courseId.toString()))).withStatus(StateStatus.UNCONSUMED)
					.withRelevancyStatus(RelevancyStatus.RELEVANT);
			StateAndRef<CourseState> courseStateRef = getServiceHub().getVaultService()
					.queryBy(CourseState.class, inputCriteria).getStates().get(0);

			CourseState outputCourseState = (CourseState) courseStateRef.getState().getData();
			outputCourseState.setCourseDec(courseDec);
			outputCourseState.setOwner(examinar);
			outputCourseState.setCoursePassScore(coursePassScore);

			TransactionBuilder txBuilder = new TransactionBuilder(notary).addInputState(courseStateRef)
					.addOutputState(outputCourseState).addCommand(new CourseCertificationContractV2.Commands.Update(),
							Arrays.asList(getOurIdentity().getOwningKey(), examinar.getOwningKey()));

			txBuilder.verify(getServiceHub());
			// Sign the transaction.
			final SignedTransaction partSignedTx = getServiceHub().signInitialTransaction(txBuilder);

			// Send the state to the counterparty, and receive it back with their signature.
			FlowSession examinerSession = initiateFlow(examinar);

			final SignedTransaction fullySignedTx = subFlow(
					new CollectSignaturesFlow(partSignedTx, Arrays.asList(examinerSession)));

			// Notarise and record the transaction in both parties' vaults.
			return subFlow(new FinalityFlow(fullySignedTx, Arrays.asList(examinerSession)));
		}

	}

	@InitiatedBy(UpdateCourseFlowInitator.class)
	public static class UpdateCourseFlowResponder extends FlowLogic<Void> {

		// private variable
		private FlowSession counterpartySession;

		public UpdateCourseFlowResponder(FlowSession counterpartySession) {
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
