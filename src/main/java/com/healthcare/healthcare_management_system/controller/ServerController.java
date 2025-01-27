package com.healthcare.healthcare_management_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ServerController {

  @GetMapping
  public String home() {
    return "Welcome to the Healthcare Management System!";
  }
}
