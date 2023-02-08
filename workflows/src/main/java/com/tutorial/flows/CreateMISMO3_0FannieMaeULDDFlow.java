package com.tutorial.flows;

import java.util.Arrays;
import java.util.Collections;

import com.tutorial.contracts.MISMO3_0FannieMaeULDDContract;
import com.tutorial.states.MISMO3_0FannieMaeULDDState;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.flows.FinalityFlow;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.InitiatingFlow;
import net.corda.core.flows.StartableByRPC;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;

public class CreateMISMO3_0FannieMaeULDDFlow {
  @InitiatingFlow
  @StartableByRPC
  public static class Initator extends FlowLogic<SignedTransaction> {
    private MISMO3_0FannieMaeULDDState.DealSet dealSet;
    private MISMO3_0FannieMaeULDDState.Parties parties;

    public Initator(MISMO3_0FannieMaeULDDState.DealSet dealSet, MISMO3_0FannieMaeULDDState.Parties parties) {
      this.dealSet = dealSet;
      this.parties = parties;
    }

    @Override
    @Suspendable
    public SignedTransaction call() throws FlowException {
      final Party notary = getServiceHub().getNetworkMapCache()
          .getNotary(CordaX500Name.parse("O=Notary,L=London,C=GB"));
      final Party me = getOurIdentity();

      UniqueIdentifier uniqueID = new UniqueIdentifier();

      MISMO3_0FannieMaeULDDState.DealSets dealSetsState = new MISMO3_0FannieMaeULDDState.DealSets(dealSet, parties,
          uniqueID, me);

      TransactionBuilder txBuilder = new TransactionBuilder(notary).addOutputState(dealSetsState).addCommand(
          new MISMO3_0FannieMaeULDDContract.Commands.Create(),
          Arrays.asList(me.getOwningKey()));

      txBuilder.verify(getServiceHub());

      // Sign the transaction.
      final SignedTransaction signedTransaction = getServiceHub().signInitialTransaction(txBuilder);

      // Notarise and record the transaction in both parties' vaults.
      return subFlow(new FinalityFlow(signedTransaction, Collections.emptyList()));
    }
  }
}
