package com.FirstSpingApp.demo.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class RoleChangeDto {

  @Positive private long userId;
  @NotBlank private String roleName;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
}
