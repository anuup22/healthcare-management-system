package com.healthcare.healthcare_management_system.controller;

import com.healthcare.healthcare_management_system.model.Patient;
import com.healthcare.healthcare_management_system.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

  @Autowired
  private PatientService patientService;

  @PostMapping
  public Patient addPatient(@RequestBody Patient patient) {
    return patientService.addPatient(patient);
  }

  @GetMapping
  public List<Patient> getAllPatients() {
    return patientService.getAllPatients();
  }

  @GetMapping("/{id}")
  public Optional<Patient> getPatientById(@PathVariable Long id) {
    return patientService.getPatientById(id);
  }

  @PutMapping("/{id}")
  public Patient updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
    return patientService.updatePatient(id, updatedPatient);
  }

  @DeleteMapping("/{id}")
  public String deletePatient(@PathVariable Long id) {
    patientService.deletePatient(id);
    return "Patient deleted successfully!";
  }
}
