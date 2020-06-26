package com.project.seatingplan.dto.request.web;

import java.util.Date;

public class CityReq {
  private int id;
  private Date createdDate;
  private String name;
  private int pincode;
  private Date updatedDate;
  private int StateId;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPincode() {
    return pincode;
  }

  public void setPincode(int pincode) {
    this.pincode = pincode;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public int getStateId() {
    return StateId;
  }

  public void setStateId(int stateId) {
    StateId = stateId;
  }
}
