package com.hospitalmanagement.hospitalapi.services;

import com.hospitalmanagement.hospitalapi.models.Appointment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {
    ResponseEntity<String> createAppointment(Appointment appointment);
    ResponseEntity<String> cancelAppointment(long appointmentId);
    ResponseEntity<String> updateAppointment(Appointment appointment, long appointmentId);
    ResponseEntity<String> deleteAppointment(long appointmentId);

    List<Appointment> getAllAppointment();
    List<Appointment> getAppointmentByPatientId(int patientId);
    List<Appointment> getAppointmentByDoctorId(int doctorId);

}
