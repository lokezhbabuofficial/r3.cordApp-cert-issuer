package com.tutorial.request;

import net.corda.v5.base.annotations.CordaSerializable;

@CordaSerializable
public class BulkStudentIssueCourseCertificationFlowRequest {
  private String participant;
  private Integer score;

  public String getParticipant() {
    return participant;
  }
  public void setParticipant(String participant) {
    this.participant = participant;
  }
  public Integer getScore() {
    return score;
  }
  public void setScore(Integer score) {
    this.score = score;
  }
}
