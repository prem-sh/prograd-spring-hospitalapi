package com.hospitalmanagement.hospitalapi.services;

import com.hospitalmanagement.hospitalapi.constants.AppConstants;
import com.hospitalmanagement.hospitalapi.models.Appointment;
import com.hospitalmanagement.hospitalapi.models.Employee;
import com.hospitalmanagement.hospitalapi.models.Patient;
import com.hospitalmanagement.hospitalapi.repositories.AppointmentRepo;
import com.hospitalmanagement.hospitalapi.repositories.EmployeeRepo;
import com.hospitalmanagement.hospitalapi.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    AppointmentRepo appointmentRepo;
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    PatientRepo patientRepo;

    @Override
    public ResponseEntity<String> createAppointment(Appointment appointment) {
        SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

        if (appointment.getPatientId() == 0) return new ResponseEntity<String>("Need patient's id", HttpStatus.BAD_REQUEST);
        if (appointment.getDoctorId() == 0) return new ResponseEntity<String>("Need Doctor's id", HttpStatus.BAD_REQUEST);
        if (!AppConstants.AppointmentStatus.validate(appointment.getStatus())) return new ResponseEntity<String>("Need Status | ACTIVE, CANCELLED, CLOSED", HttpStatus.BAD_REQUEST);
        if (appointment.getTime() == null) return new ResponseEntity<String>("Need scheduled time", HttpStatus.BAD_REQUEST);
        appointmentRepo.save(appointment);
        return new ResponseEntity<String>("Appointment scheduled successfuly", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> cancelAppointment(long appointmentId) {
        Optional<Appointment> opt = appointmentRepo.findById(appointmentId);
        if(opt.isPresent()){
            Appointment temp = opt.get();
            temp.setStatus("CANCELLED");
            appointmentRepo.save(temp);
            return new ResponseEntity<String>("Appointment Cancelled", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Appoint not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> updateAppointment(Appointment appointment, long appointmentId) {
        Optional<Appointment> opt = appointmentRepo.findById(appointmentId);
        if(opt.isPresent()){
            Appointment temp = opt.get();
            if(appointment.getStatus()!=null) {
                if(AppConstants.AppointmentStatus.validate(appointment.getStatus())) temp.setStatus(appointment.getStatus());
                else new ResponseEntity<String>("Invalid appointment status | itshould be, ACTIVE, CANCELLED, CLOSED", HttpStatus.BAD_REQUEST);
            }
            if(appointment.getCompliant()!=null) temp.setCompliant(appointment.getCompliant());
            if(appointment.getTime()!=null) temp.setTime(appointment.getTime());
            if(appointment.getPatientId()!=0) {
                Optional<Patient> patientOptional = patientRepo.findById(appointment.getPatientId());
                if(patientOptional.isPresent()){
                    temp.setPatientId(appointment.getPatientId());
                }else return new ResponseEntity<String>("Patient id not found", HttpStatus.NOT_FOUND);
            }
            if(appointment.getDoctorId()!=0){
                Optional<Employee> employeeOptional = employeeRepo.findById(appointment.getDoctorId());
                if (employeeOptional.isPresent() && employeeOptional.get().getRole().equalsIgnoreCase("doctor")){
                    temp.setDoctorId(appointment.getDoctorId());
                }else return new ResponseEntity<String>("Doctor id not found", HttpStatus.NOT_FOUND);
            }
            temp.setStatus("CANCELLED");
            appointmentRepo.save(temp);
            return new ResponseEntity<String>("Appointment updated successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Appoint not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> deleteAppointment(long appointmentId) {
        Optional<Appointment> opt = appointmentRepo.findById(appointmentId);
        if(opt.isPresent()){
            appointmentRepo.deleteById(appointmentId);
            return new ResponseEntity<String>("Appointment deleted successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Appoint not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepo.findAll();
    }

    @Override
    public List<Appointment> getAppointmentByPatientId(int patientId) {
        return appointmentRepo.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> getAppointmentByDoctorId(int doctorId) {
        Optional<Employee> opt = employeeRepo.findById(doctorId);
        if(opt.isPresent() && opt.get().getRole().equalsIgnoreCase("Doctor")){
            return appointmentRepo.findByDoctorId(doctorId);
        }else return new ArrayList<Appointment>();
    }
}
