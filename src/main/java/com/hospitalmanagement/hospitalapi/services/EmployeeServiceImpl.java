package com.hospitalmanagement.hospitalapi.services;

import com.hospitalmanagement.hospitalapi.models.Employee;
import com.hospitalmanagement.hospitalapi.models.Patient;
import com.hospitalmanagement.hospitalapi.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepo employeeRepo;
    @Override
    public ResponseEntity<String> createEmployee(Employee employee) {
        System.out.println(employee.toString());
        if (employee.getRole() == null) return new ResponseEntity<String>("Need Employee's role",HttpStatus.BAD_REQUEST);
        if (employee.getDepartment() == null) return new ResponseEntity<String>("Need Employee's department",HttpStatus.BAD_REQUEST);
        if (employee.getContact() == null) return new ResponseEntity<String>("Need Employee's contact",HttpStatus.BAD_REQUEST);
        if (employee.getName() == null) return new ResponseEntity<String>("Need Employee's name",HttpStatus.BAD_REQUEST);
        Employee newEmp = employeeRepo.save(employee);
        return new ResponseEntity<String>("New Employee created | Emp id : "+newEmp.getId(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateEmployee(Employee employee, int empId) {
        Optional<Employee> employeeOptional = employeeRepo.findById(empId);
        if (employeeOptional.isPresent()){
            Employee emp = employeeOptional.get();
            if (employee.getRole() != null) emp.setRole(employee.getRole());
            if (employee.getContact() != null) emp.setContact(employee.getContact());
            if (employee.getDepartment() != null) emp.setDepartment(employee.getDepartment());
            employeeRepo.save(emp);
        }else new ResponseEntity<String>("Employee not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<String>("Employee Updated Successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteEmployee(int empId) {
        Optional<Employee> employeeOpt = employeeRepo.findById(empId);
        if (employeeOpt.isPresent()){
            employeeRepo.deleteById(empId);
        }else new ResponseEntity<String>("Employee not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<String>("Employee Deleted Successfully", HttpStatus.OK);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    @Override
    public List<Employee> getAllEmployeeByRole(String role) {
        return employeeRepo.findByRole(role);
    }
}
