package com.FirstSpingApp.demo.services.impl;

import com.FirstSpingApp.demo.domain.Role;
import com.FirstSpingApp.demo.domain.User;
import com.FirstSpingApp.demo.domain.UserRole;
import com.FirstSpingApp.demo.exceptionhandling.exception.ResourceNotFoundException;
import com.FirstSpingApp.demo.repositories.RoleRepository;
import com.FirstSpingApp.demo.repositories.UserRepository;
import com.FirstSpingApp.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  @Autowired private UserRepository userRepository;

  @Autowired private RoleRepository roleRepository;

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User findByNameOrEmail(String name, String email) {
    return userRepository
        .findByNameOrEmail(name, email)
        .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
  }

  @Override
  public User findByName(String name) {
    return userRepository
        .findByName(name)
        .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
  }

  @Override
  public User createUser(User user) {
    user.getRoles()
        .add(
            new UserRole(
                user, roleRepository.findByName("USER").orElseGet(() -> new Role("USER"))));
    return userRepository.save(user);
  }

  @Override
  public User updateUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public void deleteByID(long id) {
    userRepository.deleteById(id);
  }

  @Override
  public User findById(long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
  }
}
