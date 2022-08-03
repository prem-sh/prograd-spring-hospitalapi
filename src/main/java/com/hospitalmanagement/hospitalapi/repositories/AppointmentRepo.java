package com.hospitalmanagement.hospitalapi.repositories;

import com.hospitalmanagement.hospitalapi.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    @Query("select a from Appointment a where a.patientId = ?1")
    List<Appointment> findByPatientId(int patientIds);

    @Query("select a from Appointment a where a.doctorId = ?1")
    List<Appointment> findByDoctorId(int doctorIds);

}
