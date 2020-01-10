package com.FirstSpingApp.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_role")
public class UserRole {
  @EmbeddedId private UserRoleId id = new UserRoleId();

  @ManyToOne
  @MapsId("userId")
  @JsonBackReference
  @NotNull
  private User user;

  @ManyToOne
  @MapsId("roleId")
  @JsonBackReference
  @NotNull
  private Role role;

  public UserRole() {}

  public UserRole(@NotNull User user, @NotNull Role role) {
    this.user = user;
    this.role = role;
  }

  public UserRoleId getId() {
    return id;
  }

  public void setId(UserRoleId id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
