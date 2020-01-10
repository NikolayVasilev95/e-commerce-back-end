package com.FirstSpingApp.demo.controllers.user;

import com.FirstSpingApp.demo.domain.ShoppingCart;
import com.FirstSpingApp.demo.resources.ShoppingCartDTO;
import com.FirstSpingApp.demo.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

@RestController
@Validated
public class ShoppingCartController {

  @Autowired private ShoppingCartService shoppingCartService;

  /** USER API */
  @GetMapping("/api/user/shopping-cart")
  public ShoppingCartDTO showShoppingCart(
      @RequestParam(value = "userId", required = true) @Positive Long userId) {
    ShoppingCart result = shoppingCartService.getShoppingCart(userId);
    return new ShoppingCartDTO(result);
  }
}
