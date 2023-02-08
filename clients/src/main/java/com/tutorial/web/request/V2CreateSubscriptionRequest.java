package com.tutorial.web.request;

public class V2CreateSubscriptionRequest {
  private String examinar;

  private String courseId;

  public String getExaminar() {
    return examinar;
  }

  public void setExaminar(String examinar) {
    this.examinar = examinar;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }
}
