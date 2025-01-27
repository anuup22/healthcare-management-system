package com.healthcare.healthcare_management_system.service;

import com.healthcare.healthcare_management_system.model.Appointment;
import com.healthcare.healthcare_management_system.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

  @Autowired
  private AppointmentRepository appointmentRepository;

  // 1. Get total number of appointments by doctor
  public Map<Long, Long> getAppointmentsPerDoctor() {
    List<Appointment> appointments = appointmentRepository.findAll();
    return appointments.stream()
        .collect(Collectors.groupingBy(appointment -> appointment.getDoctor().getId(), Collectors.counting()));
  }

  // 2. Get total appointments for a given date
  public long getAppointmentsByDate(LocalDate date) {
    List<Appointment> appointments = appointmentRepository.findAll();
    return appointments.stream()
        .filter(appointment -> appointment.getAppointmentDateTime().toLocalDate().isEqual(date))
        .count();
  }

  // 3. Get upcoming appointments count
  public long getUpcomingAppointments() {
    LocalDateTime now = LocalDateTime.now();
    return appointmentRepository.findAll().stream()
        .filter(appointment -> appointment.getAppointmentDateTime().isAfter(now) &&
            appointment.getStatus() == Appointment.AppointmentStatus.APPROVED)
        .count();
  }
}
