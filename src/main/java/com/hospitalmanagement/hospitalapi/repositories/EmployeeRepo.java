package com.hospitalmanagement.hospitalapi.repositories;

import com.hospitalmanagement.hospitalapi.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    @Query("select e from Employee e where upper(e.role) like upper(?1)")
    List<Employee> findByRole(String role);
}
