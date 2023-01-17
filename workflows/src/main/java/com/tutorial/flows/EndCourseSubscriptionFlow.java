package com.tutorial.flows;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.tutorial.contracts.CourseContract;
import com.tutorial.states.CourseSubscriptionState;

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
import net.corda.core.flows.SchedulableFlow;
import net.corda.core.flows.SignTransactionFlow;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.node.services.Vault.RelevancyStatus;
import net.corda.core.node.services.Vault.StateStatus;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;

public class EndCourseSubscriptionFlow {
  @InitiatingFlow
  @SchedulableFlow
  public static class EndCourseSubscriptionFlowInitator extends FlowLogic<SignedTransaction> {
    private UniqueIdentifier subscriptionId;

    public EndCourseSubscriptionFlowInitator(UniqueIdentifier subscriptionId) {
      this.subscriptionId = subscriptionId;
    }

    @Override
    @Suspendable
    public SignedTransaction call() throws FlowException {
      final Party notary = getServiceHub().getNetworkMapCache()
          .getNotary(CordaX500Name.parse("O=Notary,L=London,C=GB"));

      QueryCriteria.LinearStateQueryCriteria inputCriteria = new QueryCriteria.LinearStateQueryCriteria()
          .withUuid(Arrays.asList(UUID.fromString(subscriptionId.toString()))).withStatus(StateStatus.UNCONSUMED)
          .withRelevancyStatus(RelevancyStatus.RELEVANT);

      StateAndRef<CourseSubscriptionState> courseSubscriptionRef = getServiceHub().getVaultService()
          .queryBy(CourseSubscriptionState.class, inputCriteria).getStates().get(0);

      CourseSubscriptionState targetCourseSubscriptionState = (CourseSubscriptionState) courseSubscriptionRef.getState()
          .getData();

      CourseSubscriptionState outputCourseSubscriptionState = new CourseSubscriptionState(
          targetCourseSubscriptionState.getSubscriber(),
          targetCourseSubscriptionState.getCourseOwner(),
          targetCourseSubscriptionState.getLinearId(),
          targetCourseSubscriptionState.getCourseId(),
          targetCourseSubscriptionState.getSubscriptionStartDate(),
          targetCourseSubscriptionState.getSubscriptionEndDate(),
          false);

      TransactionBuilder txBuilder = new TransactionBuilder(notary).addInputState(courseSubscriptionRef)
          .addOutputState(outputCourseSubscriptionState).addCommand(new CourseContract.Commands.Update(),
              Arrays.asList(getOurIdentity().getOwningKey(),
                  outputCourseSubscriptionState.getCourseOwner().getOwningKey(),
                  outputCourseSubscriptionState.getSubscriber().getOwningKey()));

      txBuilder.verify(getServiceHub());
      // Sign the transaction.
      final SignedTransaction partSignedTx = getServiceHub().signInitialTransaction(txBuilder);

      // Set<FlowSession> sessions = outputCourseSubscriptionState.getParticipants().stream()
      //     .map(participant -> initiateFlow(participant))
      //     .collect(Collectors.toSet());
      
      FlowSession session = initiateFlow(outputCourseSubscriptionState.getSubscriber());

      final SignedTransaction fullySignedTx = subFlow(
          new CollectSignaturesFlow(partSignedTx, Arrays.asList(session)));

      return subFlow(new FinalityFlow(fullySignedTx, Arrays.asList(session)));
    }
  }

  @InitiatedBy(EndCourseSubscriptionFlowInitator.class)
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
