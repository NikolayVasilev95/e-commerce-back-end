package com.FirstSpingApp.demo.controllers.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @GetMapping(value = "/api/user-home/")
  public String home() {
    return "User home";
  }
}
