package com.FirstSpingApp.demo.domain;

import com.FirstSpingApp.demo.resources.UserDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails {

  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
  @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
  @Id
  private long id;

  @Column(name = "name")
  @NotBlank
  @Size(max = 255)
  private String name;

  @Column(name = "first_name")
  @NotBlank
  @Size(max = 255)
  private String firstName;

  @Column(name = "last_name")
  @NotBlank
  @Size(max = 255)
  private String lastName;

  @Column(name = "email")
  @Email
  @NotBlank
  @Size(max = 255)
  private String email;

  @Column(name = "user_password")
  @NotBlank
  @Size(max = 255)
  private String password;

  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      orphanRemoval = true)
  @JsonManagedReference
  private Set<UserRole> roles = new HashSet<>();

  @OneToMany(mappedBy = "user")
  @JsonManagedReference
  private List<Order> orders = new ArrayList<>();

  public User() {}

  public User(
      @NotBlank @Size(max = 255) String name,
      @NotBlank @Size(max = 255) String firstName,
      @NotBlank @Size(max = 255) String lastName,
      @Email @NotBlank @Size(max = 255) String email,
      @NotBlank @Size(max = 255) String password) {
    this.name = name;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  public User(UserDto usersDTO) {
    this(
        usersDTO.getName(),
        usersDTO.getFirstName(),
        usersDTO.getLastName(),
        usersDTO.getEmail(),
        usersDTO.getPassword());
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(UserRole::getRole).collect(Collectors.toList());
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<UserRole> getRoles() {
    return roles;
  }

  public void setRoles(Set<UserRole> roles) {
    this.roles = roles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email);
  }
}
