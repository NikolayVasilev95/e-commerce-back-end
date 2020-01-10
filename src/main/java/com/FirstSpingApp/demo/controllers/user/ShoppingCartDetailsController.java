package com.FirstSpingApp.demo.controllers.user;

import com.FirstSpingApp.demo.domain.ShoppingCartDetails;
import com.FirstSpingApp.demo.services.ShoppingCartDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
public class ShoppingCartDetailsController {

  @Autowired private ShoppingCartDetailsService shoppingCartDetailsService;

  /** USER API */
  @PostMapping(
      value = "/api/user/shopping-cart-details/add",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addProductInShoppingCart(
      @RequestBody @Valid ShoppingCartDetails shoppingCartDetails) {
    shoppingCartDetailsService.add(shoppingCartDetails);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping(value = "/api/user/shopping-cart-details/delete")
  public ResponseEntity deleteItemFromShoppingCart(
      @RequestParam(value = "id", required = true) @Positive Long id) {
    shoppingCartDetailsService.remove(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
