package com.FirstSpingApp.demo.controllers;

import com.FirstSpingApp.demo.domain.ShoppingCart;
import com.FirstSpingApp.demo.domain.User;
import com.FirstSpingApp.demo.resources.UserDto;
import com.FirstSpingApp.demo.services.ShoppingCartService;
import com.FirstSpingApp.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

  @Autowired private UserService userService;

  @Autowired private ShoppingCartService shoppingCartService;

  @Autowired private PasswordEncoder passwordEncoder;

  @PostMapping(value = "/api/register", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity register(@RequestBody UserDto usersDTO) {
    //        if (Objects.nonNull(userService.findByNameOrEmail(usersDTO.getName(),
    // usersDTO.getEmail()))){
    //            return  ResponseEntity.status(HttpStatus.CONFLICT).build();
    //        }
    usersDTO.setPassword(passwordEncoder.encode(usersDTO.getPassword()));
    User user = new User(usersDTO);
    user = userService.createUser(user);

    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.setUser(user);
    shoppingCartService.create(shoppingCart);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
