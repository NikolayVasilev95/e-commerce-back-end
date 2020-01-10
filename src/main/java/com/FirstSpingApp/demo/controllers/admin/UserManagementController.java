package com.FirstSpingApp.demo.controllers.admin;

import com.FirstSpingApp.demo.domain.User;
import com.FirstSpingApp.demo.domain.UserRole;
import com.FirstSpingApp.demo.exceptionhandling.exception.ResourceNotFoundException;
import com.FirstSpingApp.demo.repositories.RoleRepository;
import com.FirstSpingApp.demo.repositories.UserRepository;
import com.FirstSpingApp.demo.repositories.UserRoleRepository;
import com.FirstSpingApp.demo.resources.RoleChangeDto;
import com.FirstSpingApp.demo.resources.UserDto;
import com.FirstSpingApp.demo.services.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
public class UserManagementController {
  @Autowired UserService userService;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private UserRoleRepository userRoleRepository;

  @Autowired private RoleRepository roleRepository;

  @Autowired private UserRepository userRepository;

  @GetMapping(value = "/api/admin/user-management")
  public List<UserDto> userManagementViewAllUsers() {
    return userService.getAllUsers().stream().map(UserDto::new).collect(Collectors.toList());
  }

  @DeleteMapping(value = "/api/admin/user-management/delete")
  public ResponseEntity<?> userManagementDeleteUser(@RequestParam @Positive long id) {
    userService.deleteByID(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PostMapping(
      value = "/api/admin/user-management/save",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity userManagementSaveUserRole(@RequestBody @Valid RoleChangeDto dto) {

    User userFound = userService.findById(dto.getUserId());
    userFound.getRoles().clear();
    userFound
        .getRoles()
        .add(
            new UserRole(
                userFound,
                roleRepository
                    .findByName(dto.getRoleName())
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found"))));
    userRepository.save(userFound);
    Gson gson = new Gson();
    return ResponseEntity.ok(gson.toJson("Successfully change your role!"));
  }
}
