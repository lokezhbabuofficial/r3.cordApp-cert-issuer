package com.tutorial.web.request;

public class V2CourseCreateRequest {
  private String courseDec;
  private int coursePassScore;
  public String getCourseDec() {
    return courseDec;
  }
  public void setCourseDec(String courseDec) {
    this.courseDec = courseDec;
  }
  public int getCoursePassScore() {
    return coursePassScore;
  }
  public void setCoursePassScore(int coursePassScore) {
    this.coursePassScore = coursePassScore;
  }
}
