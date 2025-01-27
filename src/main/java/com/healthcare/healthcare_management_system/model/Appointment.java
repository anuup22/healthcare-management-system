package com.healthcare.healthcare_management_system.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @ManyToOne
  @JoinColumn(name = "doctor_id")
  private User doctor;

  private LocalDateTime appointmentDateTime;

  @Enumerated(EnumType.STRING)
  private AppointmentStatus status;

  public enum AppointmentStatus {
    REQUESTED,
    APPROVED,
    REJECTED,
    COMPLETED
  }
}
