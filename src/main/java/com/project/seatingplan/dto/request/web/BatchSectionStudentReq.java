package com.project.seatingplan.dto.request.web;

public class BatchSectionStudentReq {
  private int id;
  private int batchId;
  private int lkpSectionId;
  private int userId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getBatchId() {
    return batchId;
  }

  public void setBatchId(int batchId) {
    this.batchId = batchId;
  }

  public int getLkpSectionId() {
    return lkpSectionId;
  }

  public void setLkpSectionId(int lkpSectionId) {
    this.lkpSectionId = lkpSectionId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }
}
