package com.tutorial.flows;

import java.util.Arrays;

import com.tutorial.contracts.CourseCertificationContract;
import com.tutorial.states.CourseCertificationState;

import co.paralleluniverse.fibers.Suspendable;
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

public class CourseCertificationFlow {

	/**
	 * CourseCertificationFlowInitiator
	 */
	@InitiatingFlow
	@StartableByRPC
	public static class CourseCertificationFlowInitiator extends FlowLogic<SignedTransaction> {
		private Party examiner;
		private int score;

		public CourseCertificationFlowInitiator(Party examiner, int score) {
			this.examiner = examiner;
			this.score = score;
		}

		@Override
		@Suspendable
		public SignedTransaction call() throws FlowException {
			/*
			 * Obtain a reference to a notary we wish to use. /** Explicit selection of
			 * notary by CordaX500Name - argument can by coded in flows or parsed from
			 * config (Preferred)
			 */
			final Party notary = getServiceHub().getNetworkMapCache()
					.getNotary(CordaX500Name.parse("O=Notary,L=London,C=GB"));

			// Building the output AppleStamp state
			UniqueIdentifier uniqueID = new UniqueIdentifier();

			CourseCertificationState newCourseCertification = new CourseCertificationState(getOurIdentity(), examiner, score,
					uniqueID);

			TransactionBuilder txBuilder = new TransactionBuilder(notary).addOutputState(newCourseCertification).addCommand(
					new CourseCertificationContract.Commands.Issue(),
					Arrays.asList(getOurIdentity().getOwningKey(), examiner.getOwningKey()));

			txBuilder.verify(getServiceHub());

			// Sign the transaction.
			final SignedTransaction partSignedTx = getServiceHub().signInitialTransaction(txBuilder);

			// Send the state to the counterparty, and receive it back with their signature.
			FlowSession examinerSession = initiateFlow(examiner);
			final SignedTransaction fullySignedTx = subFlow(
					new CollectSignaturesFlow(partSignedTx, Arrays.asList(examinerSession)));

			// Notarise and record the transaction in both parties' vaults.
			return subFlow(new FinalityFlow(fullySignedTx, Arrays.asList(examinerSession)));
		}
	}

	@InitiatedBy(CourseCertificationFlowInitiator.class)
	public static class CourseCertificationFlowResponder extends FlowLogic<Void> {

		// private variable
		private FlowSession counterpartySession;

		public CourseCertificationFlowResponder(FlowSession counterpartySession) {
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
