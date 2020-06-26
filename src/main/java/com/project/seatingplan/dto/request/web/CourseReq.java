package com.project.seatingplan.dto.request.web;

public class CourseReq {
  private int id;
  private int departmentId;
  private int lkpCourseId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(int departmentId) {
    this.departmentId = departmentId;
  }

  public int getLkpCourseId() {
    return lkpCourseId;
  }

  public void setLkpCourseId(int lkpCourseId) {
    this.lkpCourseId = lkpCourseId;
  }
}
