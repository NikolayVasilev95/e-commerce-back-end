package com.FirstSpingApp.demo.resources;

import com.FirstSpingApp.demo.domain.Role;

public class RoleDto {
  private Long id;
  private String name;

  public RoleDto() {}

  public RoleDto(String name) {
    this.name = name;
  }

  public RoleDto(Role role) {
    id = role.getId();
    name = role.getName();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
