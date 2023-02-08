package com.tutorial.web.request;

public class V2CourseCertificationRequest {
  private String examinar;

  private String subscriptionId;

  private Integer courseScore;

  public String getExaminar() {
    return examinar;
  }

  public void setExaminar(String examinar) {
    this.examinar = examinar;
  }

  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public Integer getCourseScore() {
    return courseScore;
  }

  public void setCourseScore(int courseScore) {
    this.courseScore = courseScore;
  }
}
