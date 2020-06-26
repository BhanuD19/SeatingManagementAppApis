package com.project.seatingplan.dto.request.mobile;

public class ForgotPassword {
  private String email;
  private String newPassword;
  private String newPassword2;

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getNewPassword2() {
    return newPassword2;
  }

  public void setNewPassword2(String newPassword2) {
    this.newPassword2 = newPassword2;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
