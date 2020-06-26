package com.project.seatingplan.dto.response.web;

public class UserInstituteResp {
  private int id;
  private String userId;
  private int instituteId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getInstituteId() {
    return instituteId;
  }

  public void setInstituteId(int instituteId) {
    this.instituteId = instituteId;
  }
}
