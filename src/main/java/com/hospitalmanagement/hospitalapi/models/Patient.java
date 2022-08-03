package com.hospitalmanagement.hospitalapi.models;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@Entity
@Table(name="patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "patient_name", nullable = false)
    String name;
    @Column(name = "address")
    String address;
}
