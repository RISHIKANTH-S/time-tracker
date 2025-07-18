package com.example.TimeTracker.Repository;

import com.example.TimeTracker.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //Optional<Employee> findByUsername(String username);
    Optional<Employee> findByUserId(Long userId);

}
