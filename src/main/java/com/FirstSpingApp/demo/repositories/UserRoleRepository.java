package com.FirstSpingApp.demo.repositories;

import com.FirstSpingApp.demo.domain.UserRole;
import com.FirstSpingApp.demo.domain.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {}
