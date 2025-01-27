package com.healthcare.healthcare_management_system.service;

import com.healthcare.healthcare_management_system.model.Appointment;
import com.healthcare.healthcare_management_system.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

  @Autowired
  private AppointmentRepository appointmentRepository;

  public Appointment requestAppointment(Appointment appointment) {
    appointment.setStatus(Appointment.AppointmentStatus.REQUESTED);
    return appointmentRepository.save(appointment);
  }

  public List<Appointment> getAppointmentsForDoctor(Long doctorId) {
    return appointmentRepository.findByDoctorId(doctorId);
  }

  public List<Appointment> getAppointmentsForPatient(Long patientId) {
    return appointmentRepository.findByPatientId(patientId);
  }

  public Appointment updateAppointmentStatus(Long appointmentId, Appointment.AppointmentStatus status) {
    return appointmentRepository.findById(appointmentId).map(appointment -> {
      appointment.setStatus(status);
      return appointmentRepository.save(appointment);
    }).orElseThrow(() -> new RuntimeException("Appointment not found with id " + appointmentId));
  }
}
