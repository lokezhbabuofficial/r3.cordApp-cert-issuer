package com.tutorial.web.request;

import com.tutorial.states.MISMO3_0FannieMaeULDDState.DealSet;
import com.tutorial.states.MISMO3_0FannieMaeULDDState.Parties;

public class MISMOFanniMaeULDDRequest {
  private DealSet dealSet;
  private Parties parties;

  public DealSet getDealSet() {
    return dealSet;
  }
  public void setDealSet(DealSet dealSet) {
    this.dealSet = dealSet;
  }
  public Parties getParties() {
    return parties;
  }
  public void setParties(Parties parties) {
    this.parties = parties;
  }
}
