package com.healthcare.healthcare_management_system.controller;

import com.healthcare.healthcare_management_system.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

  @Autowired
  private ReportService reportService;

  // 1. Get total appointments by doctor
  @GetMapping("/appointments-per-doctor")
  public Map<Long, Long> getAppointmentsPerDoctor() {
    return reportService.getAppointmentsPerDoctor();
  }

  // 2. Get total appointments for a specific date
  @GetMapping("/appointments-by-date")
  public long getAppointmentsByDate(@RequestParam String date) {
    LocalDate localDate = LocalDate.parse(date); // Date format: yyyy-MM-dd
    return reportService.getAppointmentsByDate(localDate);
  }

  // 3. Get total number of upcoming appointments
  @GetMapping("/upcoming-appointments")
  public long getUpcomingAppointments() {
    return reportService.getUpcomingAppointments();
  }
}
