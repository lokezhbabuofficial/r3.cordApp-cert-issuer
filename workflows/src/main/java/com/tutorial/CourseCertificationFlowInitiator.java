package com.tutorial;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.tutorial.contracts.CourseCertificationContract;
import com.tutorial.states.CourseCertificationState;

import net.corda.systemflows.CollectSignaturesFlow;
import net.corda.systemflows.FinalityFlow;
import net.corda.v5.application.flows.BadRpcStartFlowRequestException;
import net.corda.v5.application.flows.Flow;
import net.corda.v5.application.flows.FlowSession;
import net.corda.v5.application.flows.InitiatingFlow;
import net.corda.v5.application.flows.JsonConstructor;
import net.corda.v5.application.flows.RpcStartFlowRequestParameters;
import net.corda.v5.application.flows.StartableByRPC;
import net.corda.v5.application.flows.flowservices.FlowEngine;
import net.corda.v5.application.flows.flowservices.FlowIdentity;
import net.corda.v5.application.flows.flowservices.FlowMessaging;
import net.corda.v5.application.identity.AbstractParty;
import net.corda.v5.application.identity.CordaX500Name;
import net.corda.v5.application.identity.Party;
import net.corda.v5.application.injection.CordaInject;
import net.corda.v5.application.services.IdentityService;
import net.corda.v5.application.services.json.JsonMarshallingService;
import net.corda.v5.base.annotations.Suspendable;
import net.corda.v5.ledger.UniqueIdentifier;
import net.corda.v5.ledger.contracts.Command;
import net.corda.v5.ledger.services.NotaryLookupService;
import net.corda.v5.ledger.services.TransactionService;
import net.corda.v5.ledger.transactions.SignedTransaction;
import net.corda.v5.ledger.transactions.SignedTransactionDigest;
import net.corda.v5.ledger.transactions.TransactionBuilder;
import net.corda.v5.ledger.transactions.TransactionBuilderFactory;

@InitiatingFlow
@StartableByRPC
public class CourseCertificationFlowInitiator implements Flow<SignedTransactionDigest> {
  private final RpcStartFlowRequestParameters params;

  @CordaInject
  private FlowEngine flowEngine;

  @CordaInject
  private FlowIdentity flowIdentity;

  @CordaInject
  private FlowMessaging flowMessaging;

  @CordaInject
  private TransactionService transactionService;

  @CordaInject
  private TransactionBuilderFactory transactionBuilderFactory;

  @CordaInject
  private IdentityService identityService;

  @CordaInject
  private NotaryLookupService notaryLookup;

  @CordaInject
  private JsonMarshallingService jsonMarshallingService;

  @JsonConstructor
  public CourseCertificationFlowInitiator(RpcStartFlowRequestParameters params) {
    this.params = params;
  }

  @Override
  @Suspendable
  public SignedTransactionDigest call() {
    Map<String, String> parametersMap = jsonMarshallingService.parseJson(params.getParametersInJson(), Map.class);

    Integer score;
    CordaX500Name examinarName;

    if (!parametersMap.containsKey("score"))
      throw new BadRpcStartFlowRequestException("Parameter \"score\" missing.");
    else
      score = Integer.parseInt(parametersMap.get("score"));

    if (!parametersMap.containsKey("examinarName"))
      throw new BadRpcStartFlowRequestException("Parameter \"examinarName\" missing.");
    else
      examinarName = CordaX500Name.parse(parametersMap.get("examinarName"));

    Party examinarParty = identityService.partyFromName(examinarName);
    if (examinarParty == null)
      throw new NoSuchElementException("No party found for X500 name " + examinarName);

    Party notary = notaryLookup.getNotaryIdentities().get(0);

    CourseCertificationState certificationState = new CourseCertificationState(flowIdentity.getOurIdentity(),
        examinarParty, score, new UniqueIdentifier());
    Command txCmd = new Command(new CourseCertificationContract.Commands.Issue(),
        certificationState.getParticipants().stream().map(AbstractParty::getOwningKey).collect(Collectors.toList()));

    TransactionBuilder txBuilder = transactionBuilderFactory.create()
        .setNotary(notary)
        .addOutputState(certificationState)
        .addCommand(txCmd);

    txBuilder.verify();

    SignedTransaction partSignedTx = txBuilder.sign();

    FlowSession otherPartySession = flowMessaging.initiateFlow(examinarParty);

    SignedTransaction fullySignedTx = flowEngine.subFlow(
        new CollectSignaturesFlow(
            partSignedTx,
            List.of(otherPartySession)));

    SignedTransaction notarisedTx = flowEngine.subFlow(
        new FinalityFlow(fullySignedTx, List.of(otherPartySession)));

    return new SignedTransactionDigest(
        notarisedTx.getId(),
        Collections.singletonList(jsonMarshallingService.formatJson(notarisedTx.getTx().getOutputStates().get(0))),
        notarisedTx.getSigs());
  }

  public FlowEngine getFlowEngine() {
    return flowEngine;
  }

  public IdentityService getIdentityService() {
    return identityService;
  }

  public JsonMarshallingService getJsonMarshallingService() {
    return jsonMarshallingService;
  }
}
