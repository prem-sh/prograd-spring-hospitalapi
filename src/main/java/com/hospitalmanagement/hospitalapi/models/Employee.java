package com.hospitalmanagement.hospitalapi.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String role;
    String department;
    String contact;
}
