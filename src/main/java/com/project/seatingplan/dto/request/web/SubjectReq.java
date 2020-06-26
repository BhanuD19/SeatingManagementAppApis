package com.project.seatingplan.dto.request.web;

public class SubjectReq {
  private int id;
  private String name;
  private String code;
  private int courseId;
  private int lkpSemesterId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  public int getLkpSemesterId() {
    return lkpSemesterId;
  }

  public void setLkpSemesterId(int lkpSemesterId) {
    this.lkpSemesterId = lkpSemesterId;
  }
}
