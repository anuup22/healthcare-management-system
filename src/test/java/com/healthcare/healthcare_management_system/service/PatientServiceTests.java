package com.healthcare.healthcare_management_system.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.healthcare.healthcare_management_system.model.Patient;
import com.healthcare.healthcare_management_system.repository.PatientRepository;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTests {
  @Mock
  PatientRepository patientRepository;

  @InjectMocks
  PatientService patientService;

  @BeforeAll
  public static void setup() {
    System.out.println("PatientServiceTests: BeforeAll is called");
  }

  @BeforeEach
  public void setupThis() {
    System.out.println("PatientServiceTests: BeforeEach is called");
  }

  @Test
  public void addPatient_returnsSavedPatient() {
    // Arrange
    Patient patient = Patient.builder()
        .id(1L)
        .firstName("Firstname")
        .lastName("Lastname")
        .email("patient@gmail.com")
        .phoneNumber("1234567890")
        .medicalHistory("Medical history")
        .allergies("Allergies").build();

    // Act
    Mockito.when(patientRepository.save(patient)).thenReturn(patient);
    Patient savedPatient = patientService.addPatient(patient);

    // Assert
    Assertions.assertThat(savedPatient).isNotNull();
  }
}
