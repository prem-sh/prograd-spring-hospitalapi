package com.hospitalmanagement.hospitalapi.services;

import com.hospitalmanagement.hospitalapi.models.Patient;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {
    ResponseEntity<String> createPatient(Patient Patient);
    ResponseEntity<String> updatePatient(Patient Patient, int empId);
    ResponseEntity<String> deletePatient(int empId);

    List<Patient> getAllPatient();
    List<Patient> getAllPatientByName(String name);
}
