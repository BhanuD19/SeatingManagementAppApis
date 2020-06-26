package com.project.seatingplan.dto.request.web;

import java.util.Date;

public class StudentReq {
  private int id;
  private String contactOne;
  private String contactTwo;
  private Date dob;
  private String email;
  private String enrollmentNo;
  private String fatherName;
  private String firstName;
  private String gender;
  private String lastName;
  private String motherName;
  private String rollNumber;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getContactOne() {
    return contactOne;
  }

  public void setContactOne(String contactOne) {
    this.contactOne = contactOne;
  }

  public String getContactTwo() {
    return contactTwo;
  }

  public void setContactTwo(String contactTwo) {
    this.contactTwo = contactTwo;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEnrollmentNo() {
    return enrollmentNo;
  }

  public void setEnrollmentNo(String enrollmentNo) {
    this.enrollmentNo = enrollmentNo;
  }

  public String getFatherName() {
    return fatherName;
  }

  public void setFatherName(String fatherName) {
    this.fatherName = fatherName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMotherName() {
    return motherName;
  }

  public void setMotherName(String motherName) {
    this.motherName = motherName;
  }

  public String getRollNumber() {
    return rollNumber;
  }

  public void setRollNumber(String rollNumber) {
    this.rollNumber = rollNumber;
  }
}
