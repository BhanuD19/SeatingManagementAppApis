package com.project.seatingplan.dto.request.web;

import java.util.Date;

public class UserInstituteReq {
  private int id;
  private Date createdDate;
  private int isDeleted;
  private Date updatedDate;
  private int InstituteId;
  private int UserId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public int getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(int isDeleted) {
    this.isDeleted = isDeleted;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public int getInstituteId() {
    return InstituteId;
  }

  public void setInstituteId(int instituteId) {
    InstituteId = instituteId;
  }

  public int getUserId() {
    return UserId;
  }

  public void setUserId(int userId) {
    UserId = userId;
  }
}
