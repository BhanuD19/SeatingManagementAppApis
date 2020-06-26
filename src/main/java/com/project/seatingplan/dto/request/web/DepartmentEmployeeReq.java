package com.project.seatingplan.dto.request.web;

import com.project.seatingplan.model.LkpDepartment;

public class DepartmentEmployeeReq {
  private int id;
  private int userId;
  private LkpDepartment lkpDepartment;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public LkpDepartment getLkpDepartment() {
    return lkpDepartment;
  }

  public void setLkpDepartment(LkpDepartment lkpDepartment) {
    this.lkpDepartment = lkpDepartment;
  }
}
