package com.project.seatingplan.Service;

import java.util.Random;

public class PasswordGenerator {
  public static void main(String[] args) {
    int length = 8;
    System.out.println(psw(8));
  }

  public static char[] psw(int len) {
    String Capital_letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String Small_letters = "abcdefghijklmnopqrstuvwxyz";
    String numbers = "0123456789";

    String values = Capital_letters + numbers + Small_letters;
    Random random = new Random();
    char[] password = new char[len];
    for (int i=0; i< len; i++) {
      password[i] = values.charAt(random.nextInt(values.length()));
    }
    return password;
  }
}
