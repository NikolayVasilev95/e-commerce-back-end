package com.FirstSpingApp.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderProductPK implements Serializable {

  @Column(name = "order_id")
  private Long orderId;

  @Column(name = "product_id")
  private Long productId;

  public OrderProductPK() {}

  public OrderProductPK(Long orderId, Long productId) {
    this.orderId = orderId;
    this.productId = productId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OrderProductPK)) return false;
    OrderProductPK that = (OrderProductPK) o;
    return Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, productId);
  }
}