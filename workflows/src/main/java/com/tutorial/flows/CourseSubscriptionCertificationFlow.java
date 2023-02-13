package com.tutorial.flows;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.tutorial.contracts.CourseCertificationContractV2;
import com.tutorial.states.CourseCertificationStateV2;
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
import net.corda.core.node.services.Vault.RelevancyStatus;
import net.corda.core.node.services.Vault.StateStatus;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;

public class CourseSubscriptionCertificationFlow {

  @InitiatingFlow
  @StartableByRPC
  public static class CourseSubscriptionCertificationFlowInitiator extends FlowLogic<SignedTransaction> {

    Party examinar;
    Integer courseScore;
    UniqueIdentifier subscriptionId;

    public CourseSubscriptionCertificationFlowInitiator(Party examinar, UniqueIdentifier subscriptionId,
        Integer courseScore) {
      this.examinar = examinar;
      this.subscriptionId = subscriptionId;
      this.courseScore = courseScore;
    }

    @Override
    @Suspendable
    public SignedTransaction call() throws FlowException {
      final Party notary = getServiceHub().getNetworkMapCache()
          .getNotary(CordaX500Name.parse("O=Notary,L=London,C=GB"));

      final Party me = getOurIdentity();

      UniqueIdentifier uniqueIdentifier = new UniqueIdentifier();

      List<StateAndRef<CourseSubscriptionState>> courseSubscriptionList = getServiceHub().getVaultService()
          .queryBy(CourseSubscriptionState.class)
          .getStates();

      if (courseSubscriptionList.isEmpty()) {
        throw new FlowException("No Subscriptions Avaliable");
      }
      if (!courseSubscriptionList.stream()
          .anyMatch(state -> state.getState().getData().getLinearId().equals(subscriptionId))) {
        throw new FlowException("Subscription Not Found");
      }

      QueryCriteria.LinearStateQueryCriteria inputCriteria = new QueryCriteria.LinearStateQueryCriteria()
          .withUuid(Arrays.asList(UUID.fromString(subscriptionId.toString()))).withStatus(StateStatus.UNCONSUMED)
          .withRelevancyStatus(RelevancyStatus.RELEVANT);

      List<StateAndRef<CourseSubscriptionState>> courseSubscriptionStateRefList = getServiceHub().getVaultService()
          .queryBy(CourseSubscriptionState.class, inputCriteria).getStates();

      if (courseSubscriptionStateRefList.isEmpty()) {
        throw new FlowException("Course Subscription State is not found in vault");
      }

      StateAndRef<CourseSubscriptionState> courseSubscriptionStateRef = courseSubscriptionStateRefList.get(0);

      CourseSubscriptionState courseSubscriptionState = courseSubscriptionStateRef.getState().getData();

      /*
       * Question: Is this the proper way of or can we use some custom query
       */
      if (!courseSubscriptionState.getActive()) {
        throw new FlowException("Course Subscription State is not active/expired");
      }

      CourseCertificationStateV2 certificationStateV2 = new CourseCertificationStateV2(examinar, me, courseScore,
          uniqueIdentifier);

      TransactionBuilder txBuilder = new TransactionBuilder(notary).addInputState(courseSubscriptionStateRef)
          .addOutputState(certificationStateV2).addCommand(
              new CourseCertificationContractV2.Commands.Issue(),
              Arrays.asList(me.getOwningKey(), examinar.getOwningKey()));

      txBuilder.verify(getServiceHub());

      // Sign the transaction.
      final SignedTransaction partSignedTx = getServiceHub().signInitialTransaction(txBuilder);

      // Send the state to the counterparty, and receive it back with their signature.
      FlowSession examinarSession = initiateFlow(examinar);

      final SignedTransaction fullySignedTx = subFlow(
          new CollectSignaturesFlow(partSignedTx, Arrays.asList(examinarSession)));

      // Notarise and record the transaction in both parties' vaults.
      return subFlow(new FinalityFlow(fullySignedTx, Arrays.asList(examinarSession)));
    }
  }

  @InitiatedBy(CourseSubscriptionCertificationFlowInitiator.class)
  public static class CourseSubscriptionCertificationFlowResponder extends FlowLogic<Void> {

    // private variable
    private FlowSession counterpartySession;

    public CourseSubscriptionCertificationFlowResponder(FlowSession counterpartySession) {
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
