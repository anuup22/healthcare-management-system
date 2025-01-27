package com.healthcare.healthcare_management_system.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;

  private String medicalHistory;
  private String allergies;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user; // Associate patient with a user (doctor or patient)
}
