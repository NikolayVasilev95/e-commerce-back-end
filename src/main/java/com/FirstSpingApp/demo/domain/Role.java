package com.FirstSpingApp.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "role_id_generator")
  @SequenceGenerator(name = "role_id_generator", sequenceName = "role_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "name", unique = true)
  @NotBlank
  private String name;

  @OneToMany(mappedBy = "role", orphanRemoval = true, cascade = CascadeType.ALL)
  @JsonBackReference
  private List<UserRole> users = new ArrayList<>();

  public Role() {}

  public Role(@NotBlank String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String getAuthority() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Role role = (Role) o;
    return Objects.equals(id, role.id) && Objects.equals(name, role.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  public Long getId() {
    return id;
  }
}
