package com.FirstSpingApp.demo.repositories;

import com.FirstSpingApp.demo.domain.Product;
import com.FirstSpingApp.demo.domain.ShoppingCartDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartDetailsRepository extends JpaRepository<ShoppingCartDetails, Long> {

  List<ShoppingCartDetails> findByShoppingCart_Id(long shoppingCartId);

  Optional<ShoppingCartDetails> findByShoppingCart_IdAndProducts(
      long shoppingCartId, Product product);
}
