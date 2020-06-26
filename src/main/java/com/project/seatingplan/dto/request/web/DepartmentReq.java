package com.project.seatingplan.dto.request.web;

public class DepartmentReq {
  private int id;
  private int instituteId;
  private int lkpDepartmentId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getInstituteId() {
    return instituteId;
  }

  public void setInstituteId(int instituteId) {
    this.instituteId = instituteId;
  }

  public int getLkpDepartmentId() {
    return lkpDepartmentId;
  }

  public void setLkpDepartmentId(int lkpDepartmentId) {
    this.lkpDepartmentId = lkpDepartmentId;
  }
}
