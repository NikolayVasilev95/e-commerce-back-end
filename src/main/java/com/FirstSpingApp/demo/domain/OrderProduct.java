package com.FirstSpingApp.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_product")
public class OrderProduct {

  @EmbeddedId private OrderProductPK pk;

  @ManyToOne(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JsonBackReference
  @MapsId("orderId")
  private Order order;

  @ManyToOne(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JsonBackReference
  @MapsId("productId")
  private Product product;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  public OrderProduct() {}

  public OrderProduct(OrderProductPK pk, Order order, Product product, int quantity) {
    this.pk = pk;
    this.order = order;
    this.product = product;
    this.quantity = quantity;
  }

  public OrderProductPK getPk() {
    return pk;
  }

  public void setPk(OrderProductPK pk) {
    this.pk = pk;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OrderProduct)) return false;
    OrderProduct that = (OrderProduct) o;
    return Objects.equals(pk, that.pk)
        && Objects.equals(order, that.order)
        && Objects.equals(product, that.product)
        && Objects.equals(quantity, that.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pk, order, product, quantity);
  }
}
