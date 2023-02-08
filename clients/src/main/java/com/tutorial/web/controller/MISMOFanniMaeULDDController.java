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

import com.tutorial.flows.CreateMISMO3_0FannieMaeULDDFlow;
import com.tutorial.states.MISMO3_0FannieMaeULDDState;
import com.tutorial.web.request.MISMOFanniMaeULDDRequest;
import com.tutorial.web.response.APIResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.transactions.SignedTransaction;

@RestController
@RequestMapping("/api/finnimae/loan")
@Api(value = "MISMOFanniMaeULDDController", description = "R3.Corda-POC Issue Cert - MISMO Loan Distribute", tags = "MISMO Fannie Mae ULDD")
public class MISMOFanniMaeULDDController {

  @Autowired
  BaseController baseController;

  @PostMapping(value = "/create")
  @ApiOperation(httpMethod = "POST", position = 1, value = "Create a mortage loan record in the system", response = SignedTransaction.class)
  public ResponseEntity<APIResponse<SignedTransaction>> createMISMOFanniMaeMortageLoan(
      @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody MISMOFanniMaeULDDRequest request) {


    try {
      // We block and waits for the flow to return.
      SignedTransaction result = baseController.activeUser
          .startTrackedFlowDynamic(CreateMISMO3_0FannieMaeULDDFlow.Initator.class, request.getDealSet(), request.getParties())
          .getReturnValue().get();
      return ResponseEntity.status(HttpStatus.OK).body(APIResponse.success(result));
    } catch (Exception e) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(APIResponse.error(e.getMessage()));
    }
  }

  @GetMapping(value = "/states")
  @ApiOperation(value = "List of mortage loan state", response = MISMO3_0FannieMaeULDDState.DealSets.class, responseContainer = "List")
  private ResponseEntity<List<MISMO3_0FannieMaeULDDState.DealSets>> states() {
    List<MISMO3_0FannieMaeULDDState.DealSets> state = new ArrayList<MISMO3_0FannieMaeULDDState.DealSets>();
    List<StateAndRef<MISMO3_0FannieMaeULDDState.DealSets>> courseCertificationStateAndRef = baseController.activeUser
        .vaultQuery(MISMO3_0FannieMaeULDDState.DealSets.class).getStates();
    if (courseCertificationStateAndRef.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(state);
    }
    state = courseCertificationStateAndRef.stream().map(stateAndRef -> stateAndRef.getState().getData())
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(state);
  }
}

