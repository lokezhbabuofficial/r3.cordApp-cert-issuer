// package com.tutorial;

// import com.google.common.collect.ImmutableList;
// import com.tutorial.flows.CreateAndIssueAppleStamp;
// import com.tutorial.flows.TemplateInitiator;
// import com.tutorial.states.AppleStamp;
// import com.tutorial.states.TemplateState;
// import net.corda.core.identity.CordaX500Name;
// import net.corda.core.node.services.Vault;
// import net.corda.core.node.services.vault.QueryCriteria;
// import net.corda.core.transactions.SignedTransaction;
// import net.corda.testing.node.*;
// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;

// import java.util.concurrent.Future;

// public class CreateAndIssueAppleStampTest {
//     private MockNetwork network;
//     private StartedMockNode a;
//     private StartedMockNode b;

//     @Before
//     public void setup() {
//         network = new MockNetwork(new MockNetworkParameters().withCordappsForAllNodes(ImmutableList.of(
//                         TestCordapp.findCordapp("com.tutorial.contracts"),
//                         TestCordapp.findCordapp("com.tutorial.flows")))
//                 .withNotarySpecs(ImmutableList.of(new MockNetworkNotarySpec(CordaX500Name.parse("O=Notary,L=London,C=GB"))))
//         );
//         a = network.createPartyNode(null);
//         b = network.createPartyNode(null);
//         network.runNetwork();
//     }

//     @After
//     public void tearDown() {
//         network.stopNodes();
//     }

//     @Test
//     public void dummyTest() {
//         TemplateInitiator flow = new TemplateInitiator(b.getInfo().getLegalIdentities().get(0));
//         Future<SignedTransaction> future = a.startFlow(flow);
//         network.runNetwork();

//         //successful query means the state is stored at node b's vault. Flow went through.
//         QueryCriteria inputCriteria = new QueryCriteria.VaultQueryCriteria().withStatus(Vault.StateStatus.UNCONSUMED);
//         TemplateState state = b.getServices().getVaultService()
//                 .queryBy(TemplateState.class, inputCriteria).getStates().get(0).getState().getData();
//     }

//     @Test
//     public void CreateAndIssueAppleStampTest() {
//         CreateAndIssueAppleStamp.CreateAndIssueAppleStampInitiator flow1 =
//                 new CreateAndIssueAppleStamp.CreateAndIssueAppleStampInitiator(
//                         "HoneyCrispy 4072", this.b.getInfo().getLegalIdentities().get(0));
//         Future<SignedTransaction> future1 = a.startFlow(flow1);
//         network.runNetwork();

//         //successful query means the state is stored at node b's vault. Flow went through.
//         QueryCriteria inputCriteria = new QueryCriteria.VaultQueryCriteria()
//                 .withStatus(Vault.StateStatus.UNCONSUMED);
//         AppleStamp state = b.getServices().getVaultService()
//                 .queryBy(AppleStamp.class, inputCriteria).getStates().get(0).getState().getData();
//         assert (state.getStampDesc().equals("HoneyCrispy 4072"));
//     }
// }
