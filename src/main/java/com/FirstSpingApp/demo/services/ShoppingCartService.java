package com.FirstSpingApp.demo.services;

import com.FirstSpingApp.demo.domain.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

  ShoppingCart getShoppingCartById(long id);

  ShoppingCart getShoppingCart(long userId);

  ShoppingCart create(ShoppingCart shoppingCart);

  void clear(long id);

  List<ShoppingCart> getFullShoppingCarts(List<Long> userIdList);

  public int emptyShoppingCarts(List<Long> ids);
}
