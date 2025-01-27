package com.healthcare.healthcare_management_system.service;

import com.healthcare.healthcare_management_system.model.Patient;
import com.healthcare.healthcare_management_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

  @Autowired
  private PatientRepository patientRepository;

  public Patient addPatient(Patient patient) {
    return patientRepository.save(patient);
  }

  public List<Patient> getAllPatients() {
    return patientRepository.findAll();
  }

  public Optional<Patient> getPatientById(Long id) {
    return patientRepository.findById(id);
  }

  public Patient updatePatient(Long id, Patient updatedPatient) {
    return patientRepository.findById(id).map(patient -> {
      patient.setFirstName(updatedPatient.getFirstName());
      patient.setLastName(updatedPatient.getLastName());
      patient.setEmail(updatedPatient.getEmail());
      patient.setPhoneNumber(updatedPatient.getPhoneNumber());
      patient.setMedicalHistory(updatedPatient.getMedicalHistory());
      patient.setAllergies(updatedPatient.getAllergies());
      return patientRepository.save(patient);
    }).orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
  }

  public void deletePatient(Long id) {
    patientRepository.deleteById(id);
  }
}
