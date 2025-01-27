package com.healthcare.healthcare_management_system.service;

import com.healthcare.healthcare_management_system.model.Appointment;
import com.healthcare.healthcare_management_system.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

  @Autowired
  private MailService mailService;

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
      Appointment updatedAppointment = appointmentRepository.save(appointment);

      // Send email notification
      String subject = "Appointment Status Update";
      String message = "Dear " + appointment.getPatient().getFirstName() +
          ", your appointment with Dr. " + appointment.getDoctor().getFirstName() +
          " on " + appointment.getAppointmentDateTime() +
          " has been " + status.toString().toLowerCase() + ".";
      mailService.sendEmail(appointment.getPatient().getEmail(), subject, message);

      return updatedAppointment;
    }).orElseThrow(() -> new RuntimeException("Appointment not found with id " + appointmentId));
  }

  @Scheduled(fixedRate = 60000) // Runs every minute
  public void sendAppointmentReminders() {
    List<Appointment> upcomingAppointments = appointmentRepository.findAll().stream()
        .filter(appointment -> appointment.getAppointmentDateTime().isAfter(LocalDateTime.now()) &&
            appointment.getAppointmentDateTime().isBefore(LocalDateTime.now().plusHours(1)) &&
            appointment.getStatus() == Appointment.AppointmentStatus.APPROVED)
        .toList();

    for (Appointment appointment : upcomingAppointments) {
      String subject = "Appointment Reminder";
      String message = "Dear " + appointment.getPatient().getFirstName() +
          ", this is a reminder for your appointment with Dr. " +
          appointment.getDoctor().getFirstName() +
          " scheduled at " + appointment.getAppointmentDateTime() + ".";
      mailService.sendEmail(appointment.getPatient().getEmail(), subject, message);
    }
  }
}
