package com.FirstSpingApp.demo.resources;

public class EmailModel {

  private String firstName;
  private String lastName;
  private String message;

  public EmailModel() {}

  public EmailModel(String firstName, String lastName, String message) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.message = message;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
