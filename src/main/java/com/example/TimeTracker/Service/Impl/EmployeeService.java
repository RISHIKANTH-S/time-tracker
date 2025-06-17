package com.example.TimeTracker.Service.Impl;

import com.example.TimeTracker.DTO.EmployeeDTO;
import com.example.TimeTracker.Entities.Employee;
import com.example.TimeTracker.Exceptions.ResourceNotFoundException;
import com.example.TimeTracker.Repository.EmployeeRepository;
import com.example.TimeTracker.Service.Interfaces.EmployeeServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeServiceInterface {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    // Manual constructor injection
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO registerEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Optional<Employee> existedUser=employeeRepository.findByUsername(employee.getUsername());
        if(existedUser.isPresent()){
            throw new ResourceNotFoundException("Username already exists");
        }
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUsername(employeeDTO.getUsername());
        employee.setPassword(employeeDTO.getPassword());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setEmail(employeeDTO.getEmail());
        Employee saved = employeeRepository.save(employee);
        return modelMapper.map(saved, EmployeeDTO.class);
    }
    public void deleteEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with ID: " + id);
        }
        employeeRepository.deleteById(id); // Cascades deletion of PunchEntries
    }


}
