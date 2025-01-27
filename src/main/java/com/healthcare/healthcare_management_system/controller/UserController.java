package com.healthcare.healthcare_management_system.controller;

import com.healthcare.healthcare_management_system.model.User;
import com.healthcare.healthcare_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public String register(@RequestBody User user) {
    // Encode the password before saving
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(User.Role.PATIENT); // Default role for simplicity
    userRepository.save(user);
    return "User registered successfully!";
  }

  @PostMapping("/login")
  public String login() {
    return "Login functionality handled by Spring Security";
  }
}
