package com.hospitalmanagement.hospitalapi.services;

import com.hospitalmanagement.hospitalapi.models.Patient;
import com.hospitalmanagement.hospitalapi.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientRepo patientRepo;

    @Override
    public ResponseEntity<String> createPatient(Patient patient) {
        if (patient.getName() == null) return new ResponseEntity<String>("Need patient's name",HttpStatus.BAD_REQUEST);
        Patient newPatient = patientRepo.save(patient);
        return new ResponseEntity<String>("New Patient created | Patient id : "+newPatient.getName(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updatePatient(Patient patient, int id) {
        Optional<Patient> patientOpt = patientRepo.findById(id);
        if (patientOpt.isPresent()){
            Patient pat = patientOpt.get();
            if (patient.getName() != null) pat.setName(patient.getName());
            if (patient.getAddress() != null) pat.setAddress(patient.getAddress());
            patientRepo.save(pat);
        }else new ResponseEntity<String>("Patient not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<String>("Patient Updated Successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deletePatient(int id) {
        Optional<Patient> patientOpt = patientRepo.findById(id);
        if (patientOpt.isPresent()){
            patientRepo.deleteById(id);
        }else new ResponseEntity<String>("Patient not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<String>("Patient Deleted Successfully", HttpStatus.OK);
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepo.findAll();
    }

    @Override
    public List<Patient> getAllPatientByName(String name) {
        return patientRepo.findAllByName(name);
    }
}
