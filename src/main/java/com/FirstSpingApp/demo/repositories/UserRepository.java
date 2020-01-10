package com.FirstSpingApp.demo.repositories;

import com.FirstSpingApp.demo.domain.User;
import com.FirstSpingApp.demo.resources.UserIdProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByNameOrEmail(String name, String email);

  Optional<User> findByName(String name);
}
