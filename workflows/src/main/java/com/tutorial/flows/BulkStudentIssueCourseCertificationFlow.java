package com.tutorial.flows;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
import net.corda.core.utilities.ProgressTracker;
import net.corda.core.utilities.ProgressTracker.Step;

public class BulkStudentIssueCourseCertificationFlow {

	@InitiatingFlow
	@StartableByRPC
	public static class BulkStudentIssueCourseCertificationFlowInitiator extends FlowLogic<SignedTransaction> {
		// private Map<Party, Integer> studentScoreMap;

		private List<Party> students;
		private List<Integer> studentScores;

		private final Step GENERATING_TRANSACTION = new Step(
				"Generating transaction based student and corresponding student scores");
		private final Step BUILD_ALL_STUDENT_CERTIFICATE_STATE = new Step("Building all student certification state");
		private final Step GATHERING_ALL_PUBLIC_KEYS_OF_STUDENT = new Step(
				"Gathering all the public keys of student for signature");
		private final Step VERIFYING_TRANSACTION = new Step("Verifying contract constraints.");
		private final Step SIGNING_TRANSACTION = new Step("Signing transaction with our private key.");
		private final Step GATHERING_SIGS = new Step("Gathering the counterparty's signature.") {
			@Override
			public ProgressTracker childProgressTracker() {
				return CollectSignaturesFlow.Companion.tracker();
			}
		};
		private final Step FINALISING_TRANSACTION = new Step("Obtaining notary signature and recording transaction.") {
			@Override
			public ProgressTracker childProgressTracker() {
				return FinalityFlow.Companion.tracker();
			}
		};

		private final ProgressTracker progressTracker = new ProgressTracker(GENERATING_TRANSACTION,
				BUILD_ALL_STUDENT_CERTIFICATE_STATE, GATHERING_ALL_PUBLIC_KEYS_OF_STUDENT, VERIFYING_TRANSACTION,
				SIGNING_TRANSACTION, GATHERING_SIGS, FINALISING_TRANSACTION);

		@Override
		public ProgressTracker getProgressTracker() {
			return progressTracker;
		}

		public BulkStudentIssueCourseCertificationFlowInitiator(List<Party> students, List<Integer> studentScores) {
			this.students = students;
			this.studentScores = studentScores;
		}

		@Override
		@Suspendable
		public SignedTransaction call() throws FlowException {

			final Party notary = getServiceHub().getNetworkMapCache()
					.getNotary(CordaX500Name.parse("O=Notary,L=London,C=GB"));

			Party me = getOurIdentity();

			progressTracker.setCurrentStep(BUILD_ALL_STUDENT_CERTIFICATE_STATE);
			TransactionBuilder txBuilder = new TransactionBuilder(notary);

			progressTracker.setCurrentStep(GENERATING_TRANSACTION);
			List<CourseCertificationState> courseCertificateList = buildCourseCertificateStateList(me);

			courseCertificateList.stream().forEachOrdered(courseCertificate -> txBuilder.addOutputState(courseCertificate));

			progressTracker.setCurrentStep(GATHERING_ALL_PUBLIC_KEYS_OF_STUDENT);
			List<PublicKey> publicKeyList = courseCertificateList.stream()
					.map(courseCertificate -> courseCertificate.getStudent().getOwningKey()).collect(Collectors.toList());

			publicKeyList.add(me.getOwningKey());
			txBuilder.addCommand(new CourseCertificationContract.Commands.BulkIssue(), publicKeyList);

			progressTracker.setCurrentStep(VERIFYING_TRANSACTION);
			txBuilder.verify(getServiceHub());

			progressTracker.setCurrentStep(SIGNING_TRANSACTION);
			final SignedTransaction partSignedTx = getServiceHub().signInitialTransaction(txBuilder);

			progressTracker.setCurrentStep(GATHERING_SIGS);
			Set<FlowSession> sessions = courseCertificateList.stream().map(it -> initiateFlow(it.getStudent()))
					.collect(Collectors.toSet());

			progressTracker.setCurrentStep(FINALISING_TRANSACTION);
			final SignedTransaction fullySignedTx = subFlow(new CollectSignaturesFlow(partSignedTx, sessions));

			return subFlow(new FinalityFlow(fullySignedTx, sessions));
		}

		private List<CourseCertificationState> buildCourseCertificateStateList(Party me) throws FlowException {
			List<CourseCertificationState> courseCertificateList = new ArrayList<CourseCertificationState>();

			for (int i = 0; i < students.size(); i++) {
				try {
					courseCertificateList
							.add(new CourseCertificationState(students.get(i), me, studentScores.get(i), new UniqueIdentifier()));
				} catch (Exception e) {
					throw new FlowException("Invalid flow inputs");
				}
			}
			return courseCertificateList;
		}
	}

	@InitiatedBy(BulkStudentIssueCourseCertificationFlowInitiator.class)
	public static class BulkStudentIssueCourseCertificationFlowResponder extends FlowLogic<Void> {

		private FlowSession counterpartySession;

		public BulkStudentIssueCourseCertificationFlowResponder(FlowSession counterpartySession) {
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

			subFlow(new ReceiveFinalityFlow(counterpartySession, signedTransaction.getId()));
			return null;
		}
	}
}
