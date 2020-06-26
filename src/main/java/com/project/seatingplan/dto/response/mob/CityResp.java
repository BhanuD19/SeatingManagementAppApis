package com.project.seatingplan.dto.response.mob;

public class CityResp {
  private int id;
  private String name;
  private int pincode;
  private StateResp state;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public StateResp getState() {
    return state;
  }

  public void setState(StateResp state) {
    this.state = state;
  }
}
