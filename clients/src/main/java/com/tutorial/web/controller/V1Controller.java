package com.tutorial.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.flows.CourseCertificationFlow;
import com.tutorial.states.CourseCertificationState;
import com.tutorial.web.request.V1CourseCertificationFlowRequest;
import com.tutorial.web.response.APIResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;

@RestController
@RequestMapping("/api/v1")
@Api(value = "V1Controller", description = "R3.Corda-POC Issue Cert - Use Case 1", tags = "V1")
public class V1Controller {

  @Autowired
  BaseController baseController;

  @PostMapping(value = "/course-certification")
  @ApiOperation(value = "Student requesting for course certification", response = SignedTransaction.class)
  public ResponseEntity<APIResponse<SignedTransaction>> courseCertificationFlow(
      @RequestBody V1CourseCertificationFlowRequest request) {

    CordaX500Name userCordaX500Name = CordaX500Name.parse(request.getParty());
    Party party = baseController.activeUser.wellKnownPartyFromX500Name(userCordaX500Name);

    try {
      // We block and waits for the flow to return.
      SignedTransaction result = baseController.activeUser
          .startTrackedFlowDynamic(CourseCertificationFlow.CourseCertificationFlowInitiator.class, party,
              request.getCourseScore())
          .getReturnValue().get();
      return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success(result));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(APIResponse.error(e.getMessage()));
    }
  }

  @GetMapping(value = "/states")
  private ResponseEntity<List<CourseCertificationState>> states() {
    List<CourseCertificationState> certificationStates = new ArrayList<CourseCertificationState>();
    List<StateAndRef<CourseCertificationState>> courseCertificationStateAndRef = baseController.activeUser
        .vaultQuery(CourseCertificationState.class).getStates();
    if (courseCertificationStateAndRef.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(certificationStates);
    }
    certificationStates = courseCertificationStateAndRef.stream().map(stateAndRef -> stateAndRef.getState().getData())
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(certificationStates);
  }
}
