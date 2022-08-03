package com.hospitalmanagement.hospitalapi.services;

import com.hospitalmanagement.hospitalapi.models.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    ResponseEntity<String> createEmployee(Employee employee);
    ResponseEntity<String> updateEmployee(Employee employee, int empId);
    ResponseEntity<String> deleteEmployee(int empId);

    List<Employee> getAllEmployee();
    List<Employee> getAllEmployeeByRole(String role);
}
