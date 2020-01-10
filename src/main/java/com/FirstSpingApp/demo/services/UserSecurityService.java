package com.FirstSpingApp.demo.services;

import com.FirstSpingApp.demo.domain.User;
import com.FirstSpingApp.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {
  @Autowired private UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByName(username);
    if (user.isPresent()) {
      return user.get();
    } else {
      throw new UsernameNotFoundException(String.format("Username[%s] not found"));
    }
  }
}
