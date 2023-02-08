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
import com.tutorial.states.CourseCertificationStateV2;
import com.tutorial.states.CourseState;
import com.tutorial.states.CourseSubscriptionState;
import com.tutorial.web.request.V1CourseCertificationFlowRequest;
import com.tutorial.web.response.APIResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;

@RestController
@RequestMapping("/api/v2")
@Api(value = "V2Controller", description = "R3.Corda-POC Issue Cert - Use Case 3 (Consumed and Unconsumed)", tags = "V2")
public class V2Controller {

  @Autowired
  BaseController baseController;

  @PostMapping(value = "/create-course")
  @ApiOperation(httpMethod = "POST", position = 1, value = "Examinar creating the course", response = SignedTransaction.class)
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

  @GetMapping(value = "/course-states")
  @ApiOperation(value = "List of CourseState for active user", response = CourseCertificationState.class, responseContainer = "List")
  private ResponseEntity<List<CourseState>> courseStates() {
    List<CourseState> courseStates = new ArrayList<CourseState>();
    List<StateAndRef<CourseState>> courseStatesRef = baseController.activeUser
        .vaultQuery(CourseState.class).getStates();
    if (courseStatesRef.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(courseStates);
    }
    courseStates = courseStatesRef.stream().map(stateAndRef -> stateAndRef.getState().getData())
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(courseStates);
  }

  @GetMapping(value = "/subscription-states")
  @ApiOperation(value = "List of CourseSubscriptionState for active user", response = CourseCertificationState.class, responseContainer = "List")
  private ResponseEntity<List<CourseSubscriptionState>> courseSubscriptionState() {
    List<CourseSubscriptionState> courseSubscriptionStates = new ArrayList<CourseSubscriptionState>();
    List<StateAndRef<CourseSubscriptionState>> courseSubscriptionStateAndRef = baseController.activeUser
        .vaultQuery(CourseSubscriptionState.class).getStates();
    if (courseSubscriptionStateAndRef.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(courseSubscriptionStates);
    }
    courseSubscriptionStates = courseSubscriptionStateAndRef.stream().map(stateAndRef -> stateAndRef.getState().getData())
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(courseSubscriptionStates);
  }

  @GetMapping(value = "/course-certification-states")
  @ApiOperation(value = "List of CourseCertificationStateV2 for active user", response = CourseCertificationState.class, responseContainer = "List")
  private ResponseEntity<List<CourseCertificationStateV2>> states() {
    List<CourseCertificationStateV2> certificationStates = new ArrayList<CourseCertificationStateV2>();
    List<StateAndRef<CourseCertificationStateV2>> courseCertificationStateAndRef = baseController.activeUser
        .vaultQuery(CourseCertificationStateV2.class).getStates();
    if (courseCertificationStateAndRef.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(certificationStates);
    }
    certificationStates = courseCertificationStateAndRef.stream().map(stateAndRef -> stateAndRef.getState().getData())
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(certificationStates);
  }
}
