package com.hospitalmanagement.hospitalapi.repositories;

import com.hospitalmanagement.hospitalapi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Integer> {
    @Query("select p from Patient p where upper(p.name) like upper(?1)")
    List<Patient> findAllByName(String name);
}
