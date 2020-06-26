package com.project.seatingplan.dto.request.mobile;

public class ChangePassword {
  private String oldPassword;
  private String newPassword;
  private int id;

  public String getOldPassword() {
    return oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
