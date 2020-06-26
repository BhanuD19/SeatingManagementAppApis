package com.project.seatingplan.dto.request.web;

public class StudentDetailReq {
  private int id;
  private String enrollmentNumber;
  private String rollNumber;
  private String motherName;
  private String fatherName;
  private UserReq user;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEnrollmentNumber() {
    return enrollmentNumber;
  }

  public void setEnrollmentNumber(String enrollmentNumber) {
    this.enrollmentNumber = enrollmentNumber;
  }

  public String getRollNumber() {
    return rollNumber;
  }

  public void setRollNumber(String rollNumber) {
    this.rollNumber = rollNumber;
  }

  public String getMotherName() {
    return motherName;
  }

  public void setMotherName(String motherName) {
    this.motherName = motherName;
  }

  public String getFatherName() {
    return fatherName;
  }

  public void setFatherName(String fatherName) {
    this.fatherName = fatherName;
  }

  public UserReq getUser() {
    return user;
  }

  public void setUser(UserReq user) {
    this.user = user;
  }
}
