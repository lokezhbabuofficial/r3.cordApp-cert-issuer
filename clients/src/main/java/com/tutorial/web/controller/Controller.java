package com.tutorial.web.controller;

import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.node.NodeInfo;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.tutorial.web.response.APIResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping("/api/v1")
public class Controller {

  @Autowired
  private CordaRPCOps lokeshProxy;

  @Autowired
  private CordaRPCOps varatharajProxy;

  @Autowired
  private CordaRPCOps cordaExaminarProxy;

  @Autowired
  @Qualifier("cordaExaminarProxy")
  private CordaRPCOps activeUser;

  /** Helpers for filtering the network map cache. */
  public String toDisplayString(X500Name name) {
    return BCStyle.INSTANCE.toString(name);
  }

  private boolean isNotary(NodeInfo nodeInfo) {
    return !activeUser.notaryIdentities()
        .stream().filter(el -> nodeInfo.isLegalIdentity(el))
        .collect(Collectors.toList()).isEmpty();
  }

  private boolean isMe(NodeInfo nodeInfo) {
    return nodeInfo.getLegalIdentities().get(0).getName()
        .equals(activeUser.nodeInfo().getLegalIdentities().get(0).getName());
  }

  private boolean isNetworkMap(NodeInfo nodeInfo) {
    return nodeInfo.getLegalIdentities().get(0).getName().getOrganisation().equals("Network Map Service");
  }

  @PostMapping(value = "/switch-user/{user}")
  public APIResponse<Long> switchParty(@PathVariable String user) {
    if (user.equals("Lokesh")) {
      activeUser = lokeshProxy;
    } else if (user.equals("Varatharaj")) {
      activeUser = varatharajProxy;
    } else if (user.equals("CordaExaminar")) {
      activeUser = cordaExaminarProxy;
    } else {
      return APIResponse.error("Unrecognised User");
    }
    return APIResponse.success();
  }

  @GetMapping(value = "/peers", produces = APPLICATION_JSON_VALUE)
  public HashMap<String, List<String>> getPeers() {
    HashMap<String, List<String>> myMap = new HashMap<>();

    // Find all nodes that are not notaries, ourself, or the network map.
    Stream<NodeInfo> filteredNodes = activeUser.networkMapSnapshot().stream()
        .filter(el -> !isNotary(el) && !isMe(el) && !isNetworkMap(el));
    // Get their names as strings
    List<String> nodeNames = filteredNodes.map(el -> el.getLegalIdentities().get(0).getName().toString())
        .collect(Collectors.toList());

    myMap.put("peers", nodeNames);
    return myMap;
  }

  @GetMapping(value = "/flows", produces = TEXT_PLAIN_VALUE)
  private String flows() {
    return activeUser.registeredFlows().toString();
  }
}
