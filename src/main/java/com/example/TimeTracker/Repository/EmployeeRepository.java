package com.example.TimeTracker.Repository;

import com.example.TimeTracker.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
