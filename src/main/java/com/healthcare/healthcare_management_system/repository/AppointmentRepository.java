package com.healthcare.healthcare_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.healthcare.healthcare_management_system.model.Appointment;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
  List<Appointment> findByDoctorId(Long doctorId);

  List<Appointment> findByPatientId(Long patientId);
}
