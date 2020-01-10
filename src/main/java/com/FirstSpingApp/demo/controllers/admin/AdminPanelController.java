package com.FirstSpingApp.demo.controllers.admin;

import com.FirstSpingApp.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminPanelController {
  @Autowired UserService userService;

  @GetMapping(value = "/api/admin/")
  public String adminPanelViewAllUsers() {
    return "test";
  }
}
