package com.hospitalmanagement.hospitalapi.controllers;

import com.hospitalmanagement.hospitalapi.models.Appointment;
import com.hospitalmanagement.hospitalapi.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @PostMapping
    public ResponseEntity<String> createAppointment(@RequestBody Appointment appointment){
        return appointmentService.createAppointment(appointment);
    }
    @PutMapping("{id}")
    public ResponseEntity<String> editAppointment(@RequestBody Appointment appointment, @PathVariable("id") long id){
        return appointmentService.updateAppointment(appointment, id);
    }
    @PutMapping("cancel/{id}")
    public ResponseEntity<String> cancelAppointment(@PathVariable("id") long id){
        return appointmentService.cancelAppointment(id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable long id){
        return appointmentService.deleteAppointment(id);
    }
    @GetMapping
    public List<Appointment> getAll(){
        return appointmentService.getAllAppointment();
    }
    @GetMapping("patient/{id}")
    public List<Appointment> getAllAppointmentByPatient(@PathVariable int id){
        return appointmentService.getAppointmentByPatientId(id);
    }
    @GetMapping("doctor/{id}")
    public List<Appointment> getAllAppointmentByDoctor(@PathVariable int id){
        return appointmentService.getAppointmentByDoctorId(id);
    }
}
