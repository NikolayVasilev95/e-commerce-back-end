package com.FirstSpingApp.demo.services;

import com.FirstSpingApp.demo.domain.ShoppingCartDetails;

public interface ShoppingCartDetailsService {

  ShoppingCartDetails getShoppingCartDetail(long id);

  ShoppingCartDetails add(ShoppingCartDetails shoppingCartDetails);

  void remove(Long id);

  void removeAllItems(long shoppingCartId);
}
