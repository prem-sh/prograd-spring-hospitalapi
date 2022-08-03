package com.hospitalmanagement.hospitalapi.controllers;

import com.hospitalmanagement.hospitalapi.models.Employee;
import com.hospitalmanagement.hospitalapi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }
    @PutMapping("{empId}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee, @PathVariable int empId){
        return employeeService.updateEmployee(employee, empId);
    }
    @DeleteMapping("{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int empId){
        return employeeService.deleteEmployee(empId);
    }

    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }
    @GetMapping("{role}")
    public List<Employee> getAllEmployeeByRole(@PathVariable String role){
        return employeeService.getAllEmployeeByRole(role);
    }
}
