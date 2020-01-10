package com.FirstSpingApp.demo.resources;

import com.FirstSpingApp.demo.domain.ShoppingCart;
import com.FirstSpingApp.demo.domain.ShoppingCartDetails;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDTO {

  private Long id;
  private List<ShoppingCartDetails> shoppingCartDetails = new ArrayList<>();

  public ShoppingCartDTO() {}

  public ShoppingCartDTO(ShoppingCart shoppingCart) {
    id = shoppingCart.getId();
    shoppingCartDetails = shoppingCart.getShoppingCartDetails();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<ShoppingCartDetails> getShoppingCartDetails() {
    return shoppingCartDetails;
  }

  public void setShoppingCartDetails(List<ShoppingCartDetails> shoppingCartDetails) {
    this.shoppingCartDetails = shoppingCartDetails;
  }
}
