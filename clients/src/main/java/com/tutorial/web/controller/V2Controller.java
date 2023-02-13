package com.tutorial.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.flows.CourseSubscriptionCertificationFlow;
import com.tutorial.flows.CreateCourseFlow;
import com.tutorial.flows.SubscribeToCourseFlow;
import com.tutorial.states.CourseCertificationState;
import com.tutorial.states.CourseCertificationStateV2;
import com.tutorial.states.CourseState;
import com.tutorial.states.CourseSubscriptionState;
import com.tutorial.web.request.V2CourseCertificationRequest;
import com.tutorial.web.request.V2CourseCreateRequest;
import com.tutorial.web.request.V2CreateSubscriptionRequest;
import com.tutorial.web.response.APIResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.contracts.UniqueIdentifier;
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
      @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody V2CourseCreateRequest request) {

    try {
      // We block and waits for the flow to return.
      SignedTransaction result = baseController.activeUser
          .startTrackedFlowDynamic(CreateCourseFlow.CreateCourseFlowInitator.class, request.getCourseDec(),
              request.getCoursePassScore())
          .getReturnValue().get();
      return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success(result));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(APIResponse.error(e.getMessage()));
    }
  }

  @PostMapping(value = "/create-subscription")
  @ApiOperation(httpMethod = "POST", position = 1, value = "Examinar creating the subscription for students", response = SignedTransaction.class)
  public ResponseEntity<APIResponse<SignedTransaction>> courseSubscriptionFlow(
      @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody V2CreateSubscriptionRequest request) {
    CordaX500Name userCordaX500Name = CordaX500Name.parse(request.getStudent());
    Party party = baseController.activeUser.wellKnownPartyFromX500Name(userCordaX500Name);

    UniqueIdentifier courseId = new UniqueIdentifier(null, UUID.fromString(request.getCourseId()));
    try {
      // We block and waits for the flow to return.
      SignedTransaction result = baseController.activeUser
          .startTrackedFlowDynamic(SubscribeToCourseFlow.SubscribeToCourseFlowInitiator.class, party,
              courseId)
          .getReturnValue().get();
      return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success(result));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(APIResponse.error(e.getMessage()));
    }
  }

  @PostMapping(value = "/cretification")
  @ApiOperation(httpMethod = "POST", position = 1, value = "Students creating the certification by their subscription", response = SignedTransaction.class)
  public ResponseEntity<APIResponse<SignedTransaction>> courseCertifiactionFlow(
      @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody V2CourseCertificationRequest request) {
    CordaX500Name userCordaX500Name = CordaX500Name.parse(request.getExaminar());
    Party party = baseController.activeUser.wellKnownPartyFromX500Name(userCordaX500Name);

    UniqueIdentifier subscriptionId = new UniqueIdentifier(null, UUID.fromString(request.getSubscriptionId()));
    try {
      // We block and waits for the flow to return.
      SignedTransaction result = baseController.activeUser
          .startTrackedFlowDynamic(
              CourseSubscriptionCertificationFlow.CourseSubscriptionCertificationFlowInitiator.class, party,
              subscriptionId, request.getCourseScore())
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
    courseSubscriptionStates = courseSubscriptionStateAndRef.stream()
        .map(stateAndRef -> stateAndRef.getState().getData())
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
