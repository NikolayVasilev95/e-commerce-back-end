package com.FirstSpingApp.demo.resources;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
public class OrderPostDto {

  @NotEmpty @UniqueElements private List<@Valid ProductOrderPostDTO> products;

  @Positive private long userId;

  public List<ProductOrderPostDTO> getProducts() {
    return products;
  }

  public void setProducts(List<ProductOrderPostDTO> products) {
    this.products = products;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }
}
