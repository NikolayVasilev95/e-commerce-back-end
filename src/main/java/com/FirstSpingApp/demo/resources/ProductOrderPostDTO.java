package com.FirstSpingApp.demo.resources;

import com.FirstSpingApp.demo.domain.OrderProduct;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class ProductOrderPostDTO {

  @NotNull @Positive private Long productId;
  @Positive private int quantity;

  public ProductOrderPostDTO() {};

  public ProductOrderPostDTO(OrderProduct orderProduct) {
    productId = orderProduct.getProduct().getId();
    quantity = orderProduct.getQuantity();
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ProductOrderPostDTO)) return false;
    ProductOrderPostDTO that = (ProductOrderPostDTO) o;
    return Objects.equals(productId, that.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId);
  }
}
