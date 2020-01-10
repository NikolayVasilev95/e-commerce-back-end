package com.FirstSpingApp.demo.services;

import com.FirstSpingApp.demo.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

  List<User> getAllUsers();

  User findByNameOrEmail(String name, String email);

  User findByName(String name);

  User createUser(User user);

  User updateUser(User user);

  void deleteByID(long id);

  User findById(long id);
}
