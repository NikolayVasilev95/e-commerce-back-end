package com.FirstSpingApp.demo.resources;

import com.FirstSpingApp.demo.domain.OrderProduct;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

public class ProductOrderDto {

  private String productName;
  private BigDecimal price;
  private int quantity;

  public ProductOrderDto() {};

  public ProductOrderDto(OrderProduct orderProduct) {
    productName = orderProduct.getProduct().getName();
    price = orderProduct.getProduct().getPrice();
    quantity = orderProduct.getQuantity();
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
