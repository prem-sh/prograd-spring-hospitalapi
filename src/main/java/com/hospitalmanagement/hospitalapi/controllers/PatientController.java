package com.hospitalmanagement.hospitalapi.controllers;

import com.hospitalmanagement.hospitalapi.models.Patient;
import com.hospitalmanagement.hospitalapi.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    @PostMapping
    public ResponseEntity<String> createPatient(@RequestBody Patient patient){
        return patientService.createPatient(patient);
    }
    @PutMapping("{empId}")
    public ResponseEntity<String> updatePatient(@RequestBody Patient patient, @PathVariable  int empId){
        return patientService.updatePatient(patient, empId);
    }
    @DeleteMapping("{empId}")
    public ResponseEntity<String> deletePatient(@PathVariable int empId){
        return patientService.deletePatient(empId);
    }

    @GetMapping
    public List<Patient> getAllPatient(){
        return patientService.getAllPatient();
    }
    @GetMapping("{name}")
    public List<Patient> getAllPatientByName(@PathVariable String name){
        return patientService.getAllPatientByName(name);
    }

}
