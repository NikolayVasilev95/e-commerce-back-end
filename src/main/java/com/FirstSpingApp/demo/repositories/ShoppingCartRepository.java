package com.FirstSpingApp.demo.repositories;

import com.FirstSpingApp.demo.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

  ShoppingCart findByUserId(long userId);

  List<ShoppingCart> findDistinctByShoppingCartDetailsIsNotEmptyAndUser_IdNotIn(
      List<Long> userIdList);

  List<ShoppingCart> findAllByUserIdIn(Iterable<Long> ids);
}
