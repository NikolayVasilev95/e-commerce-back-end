package com.FirstSpingApp.demo.resources;

import com.FirstSpingApp.demo.domain.User;
import com.FirstSpingApp.demo.domain.UserRole;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
  private Long id;
  private String name;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private List<RoleDto> roles;

  public UserDto() {}

  public UserDto(User user) {
    id = user.getId();
    name = user.getName();
    firstName = user.getFirstName();
    lastName = user.getLastName();
    email = user.getEmail();
    password = user.getPassword();
    roles =
        user.getRoles().stream()
            .map(UserRole::getRole)
            .map(RoleDto::new)
            .collect(Collectors.toList());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getId() {
    return this.id;
  }

  public List<RoleDto> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleDto> roles) {
    this.roles = roles;
  }
}
