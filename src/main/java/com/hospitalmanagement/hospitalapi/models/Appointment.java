package com.hospitalmanagement.hospitalapi.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(name = "patient", nullable = false)
    int patientId;
    @Column(name = "doctor", nullable = false)
    int doctorId;
    @Column(name = "compliant")
    String compliant;
    @Column(name = "status", nullable = false)
    String status;
    @Temporal(TemporalType.TIMESTAMP)
    Date time;
}
