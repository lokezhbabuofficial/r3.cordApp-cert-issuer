package com.tutorial.web.controller;

import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.node.NodeInfo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tutorial.web.response.APIResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping("/api/base")
@Api(value = "BaseController", description = "R3.Corda-POC Issue Cert - Base", tags = "Base")
public class BaseController {

  @Autowired
  public CordaRPCOps lokeshProxy;

  @Autowired
  public CordaRPCOps varatharajProxy;

  @Autowired
  public CordaRPCOps cordaExaminarProxy;

  @Autowired
  @Qualifier("cordaExaminarProxy")
  public CordaRPCOps activeUser;

  /** Helpers for filtering the network map cache. */
  public String toDisplayString(X500Name name) {
    return BCStyle.INSTANCE.toString(name);
  }

  public boolean isNotary(NodeInfo nodeInfo) {
    return !activeUser.notaryIdentities()
        .stream().filter(el -> nodeInfo.isLegalIdentity(el))
        .collect(Collectors.toList()).isEmpty();
  }

  public boolean isMe(NodeInfo nodeInfo) {
    return nodeInfo.getLegalIdentities().get(0).getName()
        .equals(activeUser.nodeInfo().getLegalIdentities().get(0).getName());
  }

  public boolean isNetworkMap(NodeInfo nodeInfo) {
    return nodeInfo.getLegalIdentities().get(0).getName().getOrganisation().equals("Network Map Service");
  }

  @GetMapping(value = "/active-user", produces = APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Active User")
  private ResponseEntity<HashMap<String, String>> whoami() {
    HashMap<String, String> myMap = new HashMap<>();
    myMap.put("me", activeUser.nodeInfo().getLegalIdentities().get(0).getName().toString());
    return ResponseEntity.status(HttpStatus.OK).body(myMap);
  }

  @PostMapping(value = "/switch-user/{user}")
  @ApiOperation(value = "Switch between users (party)")
  private ResponseEntity<APIResponse<CordaRPCOps>> switchParty(@PathVariable String user) {
    if (user.equals("Lokesh")) {
      activeUser = lokeshProxy;
    } else if (user.equals("Varatharaj")) {
      activeUser = varatharajProxy;
    } else if (user.equals("CordaExaminar")) {
      activeUser = cordaExaminarProxy;
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.error("Unrecognised User"));
    }
    return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success(activeUser));
  }

  @GetMapping(value = "/peers", produces = APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Active user peers")
  private ResponseEntity<HashMap<String, List<String>>> getPeers() {
    HashMap<String, List<String>> myMap = new HashMap<>();

    // Find all nodes that are not notaries, ourself, or the network map.
    Stream<NodeInfo> filteredNodes = activeUser.networkMapSnapshot().stream()
        .filter(el -> !isNotary(el) && !isMe(el) && !isNetworkMap(el));
    // Get their names as strings
    List<String> nodeNames = filteredNodes.map(el -> el.getLegalIdentities().get(0).getName().toString())
        .collect(Collectors.toList());

    myMap.put("peers", nodeNames);
    return ResponseEntity.status(HttpStatus.OK).body(myMap);
  }

  @GetMapping(value = "/flows", produces = APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Active user flow list")
  private ResponseEntity<List<String>> flows() {
    return ResponseEntity.status(HttpStatus.OK).body(activeUser.registeredFlows());
  }

  @GetMapping(value = "/servertime", produces = TEXT_PLAIN_VALUE)
  @ApiOperation(value = "Server Time")
  private String serverTime() {
    return (LocalDateTime.ofInstant(activeUser.currentNodeTime(), ZoneId.of("UTC"))).toString();
  }

  @GetMapping(value = "/addresses", produces = TEXT_PLAIN_VALUE)
  @ApiOperation(value = "Address")
  private String addresses() {
    return activeUser.nodeInfo().getAddresses().toString();
  }

  @GetMapping(value = "/identities", produces = TEXT_PLAIN_VALUE)
  @ApiOperation(value = "Identities")
  private String identities() {
    return activeUser.nodeInfo().getLegalIdentities().toString();
  }

  @GetMapping(value = "/platformversion", produces = TEXT_PLAIN_VALUE)
  @ApiOperation(value = "Platform version")
  private String platformVersion() {
    return Integer.toString(activeUser.nodeInfo().getPlatformVersion());
  }

  @GetMapping(value = "/notaries", produces = TEXT_PLAIN_VALUE)
  @ApiOperation(value = "Notary List")
  private String notaries() {
    return activeUser.notaryIdentities().toString();
  }
}
