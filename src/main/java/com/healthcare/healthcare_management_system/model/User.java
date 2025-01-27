package com.healthcare.healthcare_management_system.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"user\"") // user is a reserved keyword in PostgreSQL
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;
  private String email;
  private String password; // We’ll encrypt this later with Spring Security

  @Enumerated(EnumType.STRING)
  private Role role;

  public enum Role {
    ADMIN,
    DOCTOR,
    NURSE,
    PATIENT
  }
}
