package com.FirstSpingApp.demo.resources;

import com.FirstSpingApp.demo.domain.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {
  private Long id;
  private LocalDate dateCreated;
  private String status;
  private List<ProductOrderDto> orderDetails = new ArrayList<>();

  public OrderDto() {}

  public OrderDto(Order order) {
    id = order.getId();
    dateCreated = order.getDateCreated();
    status = order.getStatus();
    order.getOrderProducts().stream().map(ProductOrderDto::new).forEach(orderDetails::add);
  }

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

  public List<ProductOrderDto> getOrderDetails() {
    return orderDetails;
  }

  public void setOrderDetails(List<ProductOrderDto> orderDetails) {
    this.orderDetails = orderDetails;
  }
}
