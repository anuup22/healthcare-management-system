package com.healthcare.healthcare_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.healthcare.healthcare_management_system.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}