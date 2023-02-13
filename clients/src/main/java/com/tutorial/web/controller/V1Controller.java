package com.tutorial.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.flows.BulkStudentIssueCourseCertificationFlow;
import com.tutorial.flows.CourseCertificationFlow;
import com.tutorial.states.CourseCertificationState;
import com.tutorial.web.request.V1CourseCertificationFlowRequest;
import com.tutorial.web.response.APIResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;

@RestController
@RequestMapping("/api/v1")
@Api(value = "V1Controller", description = "R3.Corda-POC Issue Cert - Use Case 1&2", tags = "V1")
public class V1Controller {

  @Autowired
  BaseController baseController;

  @PostMapping(value = "/course-certification")
  @ApiOperation(httpMethod = "POST", position = 1, value = "Student requesting for course certification", response = SignedTransaction.class)
  public ResponseEntity<APIResponse<SignedTransaction>> courseCertificationFlow(
      @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody V1CourseCertificationFlowRequest request) {

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

  @PostMapping(value = "/bulk-course-certification")
  @ApiOperation(httpMethod = "POST", position = 2, value = "Examinar issuing certification for multiple students (parties)", response = SignedTransaction.class, responseContainer = "List")
  public ResponseEntity<APIResponse<SignedTransaction>> bulkCourseCertificationFlow(
      @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody List<V1CourseCertificationFlowRequest> request) {

    List<Party> students = new ArrayList<Party>();
    List<Integer> studentScores = new ArrayList<Integer>();

    request.forEach(req -> {
      CordaX500Name userCordaX500Name = CordaX500Name.parse(req.getParty());
      Party party = baseController.activeUser.wellKnownPartyFromX500Name(userCordaX500Name);
      students.add(party);
      studentScores.add(req.getCourseScore());
    });

    try {
      // We block and waits for the flow to return.
      SignedTransaction result = baseController.activeUser
          .startTrackedFlowDynamic(
              BulkStudentIssueCourseCertificationFlow.BulkStudentIssueCourseCertificationFlowInitiator.class, students,
              studentScores)
          .getReturnValue().get();
      return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success(result));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(APIResponse.error(e.getMessage()));
    }
  }

  @GetMapping(value = "/states")
  @ApiOperation(value = "List of CourseCertificationState for active user", response = CourseCertificationState.class, responseContainer = "List")
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
