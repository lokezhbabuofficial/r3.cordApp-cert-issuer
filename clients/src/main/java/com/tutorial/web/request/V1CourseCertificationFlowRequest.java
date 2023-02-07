package com.tutorial.web.request;

import io.swagger.annotations.ApiModelProperty;

public class V1CourseCertificationFlowRequest {

  @ApiModelProperty(notes = "Name of the Examinar", name = "party", required = true, value = "O=CordaExaminar,L=Northern Cape,C=ZA")
  private String party;

  @ApiModelProperty(notes = "Course Score", name = "courseScore", required = true, value = "87")
  private Integer courseScore;

  public String getParty() {
    return party;
  }

  public void setParty(String party) {
    this.party = party;
  }

  public Integer getCourseScore() {
    return courseScore;
  }

  public void setCourseScore(Integer courseScore) {
    this.courseScore = courseScore;
  }

}
