package com.tutorial;

import java.security.PublicKey;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.tutorial.contracts.CourseCertificationContract;
import com.tutorial.request.BulkStudentIssueCourseCertificationFlowRequest;
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
import net.corda.v5.application.identity.CordaX500Name;
import net.corda.v5.application.identity.Party;
import net.corda.v5.application.injection.CordaInject;
import net.corda.v5.application.services.IdentityService;
import net.corda.v5.application.services.json.JsonMarshallingService;
import net.corda.v5.base.annotations.Suspendable;
import net.corda.v5.ledger.UniqueIdentifier;
import net.corda.v5.ledger.services.NotaryLookupService;
import net.corda.v5.ledger.services.TransactionService;
import net.corda.v5.ledger.transactions.SignedTransaction;
import net.corda.v5.ledger.transactions.SignedTransactionDigest;
import net.corda.v5.ledger.transactions.TransactionBuilder;
import net.corda.v5.ledger.transactions.TransactionBuilderFactory;

@InitiatingFlow
@StartableByRPC
public class BulkStudentIssueCourseCertificationFlowInitator implements Flow<SignedTransactionDigest> {

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
  public BulkStudentIssueCourseCertificationFlowInitator(RpcStartFlowRequestParameters params) {
    this.params = params;
  }

  @Override
  @Suspendable
  public SignedTransactionDigest call() {

    List<BulkStudentIssueCourseCertificationFlowRequest> bulkCourseCertificationFlowRequests = jsonMarshallingService
        .parseJsonList(params.getParametersInJson(), BulkStudentIssueCourseCertificationFlowRequest.class);

    if (bulkCourseCertificationFlowRequests.isEmpty()) {
      throw new BadRpcStartFlowRequestException("Request is empty");
    }
    Party examinar = flowIdentity.getOurIdentity();
    Party notary = notaryLookup.getNotaryIdentities().get(0);

    List<CourseCertificationState> courseCertificateList = bulkCourseCertificationFlowRequests.stream()
        .map(request -> new CourseCertificationState(
            identityService.partyFromName(CordaX500Name.parse(request.getParticipant())), examinar,
            request.getScore(), new UniqueIdentifier()))
        .collect(Collectors.toList());

    TransactionBuilder txBuilder = transactionBuilderFactory.create().setNotary(notary);
    courseCertificateList.stream().forEachOrdered(courseCertificate -> txBuilder.addOutputState(courseCertificate));

    List<PublicKey> publicKeyList = courseCertificateList.stream()
        .map(courseCertificate -> courseCertificate.getStudent().getOwningKey()).collect(Collectors.toList());
    publicKeyList.add(examinar.getOwningKey());
    txBuilder.addCommand(new CourseCertificationContract.Commands.BulkIssue(), publicKeyList);

    txBuilder.verify();

    SignedTransaction partSignedTx = txBuilder.sign();
    Set<FlowSession> sessions = courseCertificateList.stream().map(it -> flowMessaging.initiateFlow(it.getStudent()))
        .collect(Collectors.toSet());

    SignedTransaction fullySignedTx = flowEngine.subFlow(
        new CollectSignaturesFlow(
            partSignedTx,
            sessions));

    SignedTransaction notarisedTx = flowEngine.subFlow(
        new FinalityFlow(fullySignedTx, sessions));

    return new SignedTransactionDigest(
        notarisedTx.getId(),
        Collections.singletonList(jsonMarshallingService.formatJson(setOutput(notarisedTx))),
        notarisedTx.getSigs());
  }

  private Map<String, String> setOutput(SignedTransaction notarisedTx) {
    return notarisedTx.getTx().getOutputStates().stream()
        .map(state -> (CourseCertificationState) state)
        .collect(Collectors.toMap(x -> x.getLinearId().toString(), CourseCertificationState::toJsonString));
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
