package com.healthcare.healthcare_management_system.controller;

import com.healthcare.healthcare_management_system.model.Appointment;
import com.healthcare.healthcare_management_system.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

  @Autowired
  private AppointmentService appointmentService;

  @PostMapping
  public Appointment requestAppointment(@RequestBody Appointment appointment) {
    return appointmentService.requestAppointment(appointment);
  }

  @GetMapping("/doctor/{doctorId}")
  public List<Appointment> getAppointmentsForDoctor(@PathVariable Long doctorId) {
    return appointmentService.getAppointmentsForDoctor(doctorId);
  }

  @GetMapping("/patient/{patientId}")
  public List<Appointment> getAppointmentsForPatient(@PathVariable Long patientId) {
    return appointmentService.getAppointmentsForPatient(patientId);
  }

  @PutMapping("/{appointmentId}/status")
  public Appointment updateAppointmentStatus(@PathVariable Long appointmentId, @RequestParam String status) {
    return appointmentService.updateAppointmentStatus(
        appointmentId, Appointment.AppointmentStatus.valueOf(status.toUpperCase()));
  }
}
