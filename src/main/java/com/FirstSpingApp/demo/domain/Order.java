package com.FirstSpingApp.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "order_id_generator")
  @SequenceGenerator(name = "order_id_generator", sequenceName = "order_id_seq", allocationSize = 1)
  private Long id;

  @JsonFormat(pattern = "dd/MM/yyyy")
  @NotNull
  @Column(name = "date_created")
  private LocalDate dateCreated;

  @NotBlank
  @Column(name = "status")
  private String status;

  @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
  @JsonManagedReference
  @Valid
  private List<OrderProduct> orderProducts = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @JsonBackReference
  @Valid
  private User user;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDate dateCreated) {
    this.dateCreated = dateCreated;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<OrderProduct> getOrderProducts() {
    return orderProducts;
  }

  public void setOrderProducts(List<OrderProduct> orderProducts) {
    this.orderProducts = orderProducts;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
