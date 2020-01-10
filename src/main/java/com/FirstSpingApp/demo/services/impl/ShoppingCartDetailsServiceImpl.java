package com.FirstSpingApp.demo.services.impl;

import com.FirstSpingApp.demo.domain.ShoppingCartDetails;
import com.FirstSpingApp.demo.exceptionhandling.exception.ResourceNotFoundException;
import com.FirstSpingApp.demo.repositories.ShoppingCartDetailsRepository;
import com.FirstSpingApp.demo.services.ProductService;
import com.FirstSpingApp.demo.services.ShoppingCartDetailsService;
import com.FirstSpingApp.demo.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartDetailsServiceImpl implements ShoppingCartDetailsService {

  @Autowired private ShoppingCartDetailsRepository shoppingCartDetailsRepository;

  @Autowired private ShoppingCartService shoppingCartService;

  @Autowired private ProductService productService;

  @Override
  public ShoppingCartDetails getShoppingCartDetail(long id) {
    return shoppingCartDetailsRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Shopping cart details not found"));
  }

  @Override
  public ShoppingCartDetails add(ShoppingCartDetails shoppingCartDetails) {
    productService.getProduct(shoppingCartDetails.getProducts().getId());
    shoppingCartService.getShoppingCartById(shoppingCartDetails.getShoppingCart().getId());
    Optional<ShoppingCartDetails> details =
        shoppingCartDetailsRepository.findByShoppingCart_IdAndProducts(
            shoppingCartDetails.getShoppingCart().getId(), shoppingCartDetails.getProducts());
    if (details.isPresent()) {
      int newQuantity = shoppingCartDetails.getQuantity();
      shoppingCartDetails = details.get();
      shoppingCartDetails.setQuantity(shoppingCartDetails.getQuantity() + newQuantity);
    }
    return shoppingCartDetailsRepository.save(shoppingCartDetails);
  }

  @Override
  public void remove(Long id) {
    getShoppingCartDetail(id);
    shoppingCartDetailsRepository.deleteById(id);
  }

  @Override
  public void removeAllItems(long shoppingCartId) {
    List<ShoppingCartDetails> details =
        shoppingCartDetailsRepository.findByShoppingCart_Id(shoppingCartId);
    shoppingCartDetailsRepository.deleteInBatch(details);
  }
}
