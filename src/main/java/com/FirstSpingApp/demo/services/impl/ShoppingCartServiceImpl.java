package com.FirstSpingApp.demo.services.impl;

import com.FirstSpingApp.demo.domain.ShoppingCart;
import com.FirstSpingApp.demo.exceptionhandling.exception.ResourceNotFoundException;
import com.FirstSpingApp.demo.repositories.ShoppingCartRepository;
import com.FirstSpingApp.demo.services.ShoppingCartDetailsService;
import com.FirstSpingApp.demo.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  @Autowired private ShoppingCartRepository shoppingCartRepository;

  @Autowired private ShoppingCartDetailsService shoppingCartDetailsService;

  @Override
  public ShoppingCart getShoppingCartById(long id) {
    return shoppingCartRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Shopping cart does not exist"));
  }

  @Override
  public ShoppingCart getShoppingCart(long userId) {
    return shoppingCartRepository.findByUserId(userId);
  }

  @Override
  public ShoppingCart create(ShoppingCart shoppingCart) {
    return shoppingCartRepository.save(shoppingCart);
  }

  @Override
  public void clear(long id) {
    shoppingCartDetailsService.removeAllItems(id);
  }

  @Override
  public List<ShoppingCart> getFullShoppingCarts(List<Long> userIdList) {
    if (userIdList.isEmpty()) {
      userIdList.add(0L);
    }
    return shoppingCartRepository.findDistinctByShoppingCartDetailsIsNotEmptyAndUser_IdNotIn(
        userIdList);
  }

  @Override
  public int emptyShoppingCarts(List<Long> ids) {
    List<ShoppingCart> carts = shoppingCartRepository.findAllByUserIdIn(ids);
    carts.stream()
        .mapToLong(ShoppingCart::getId)
        .forEach(shoppingCartDetailsService::removeAllItems);
    return carts.size();
  }
}
