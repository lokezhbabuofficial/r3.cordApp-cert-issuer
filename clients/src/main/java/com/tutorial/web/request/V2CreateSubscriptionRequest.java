package com.tutorial.web.request;

public class V2CreateSubscriptionRequest {
  private String student;

  public void setStudent(String student) {
    this.student = student;
  }

  public String getStudent() {
    return student;
  }

  private String courseId;


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }
}
