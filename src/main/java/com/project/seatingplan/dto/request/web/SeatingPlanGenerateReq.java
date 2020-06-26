package com.project.seatingplan.dto.request.web;

import java.util.Date;
import java.util.List;

public class SeatingPlanGenerateReq {
  private List<SeatingPlanBatchReq> batchInfoViewArray;
  private List<String> roomNameArray;
  private List<Integer> studentCountArray;
  private Date examDate;
  private String examDetail;

  public List<SeatingPlanBatchReq> getBatchInfoViewArray() {
    return batchInfoViewArray;
  }

  public void setBatchInfoViewArray(List<SeatingPlanBatchReq> batchInfoViewArray) {
    this.batchInfoViewArray = batchInfoViewArray;
  }

  public List<String> getRoomNameArray() {
    return roomNameArray;
  }

  public void setRoomNameArray(List<String> roomNameArray) {
    this.roomNameArray = roomNameArray;
  }

  public List<Integer> getStudentCountArray() {
    return studentCountArray;
  }

  public void setStudentCountArray(List<Integer> studentCountArray) {
    this.studentCountArray = studentCountArray;
  }

  public Date getExamDate() {
    return examDate;
  }

  public void setExamDate(Date examDate) {
    this.examDate = examDate;
  }

  public String getExamDetail() {
    return examDetail;
  }

  public void setExamDetail(String examDetail) {
    this.examDetail = examDetail;
  }
}
