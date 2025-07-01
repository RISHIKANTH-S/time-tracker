package com.example.TimeTracker.Service.Impl;

import com.example.TimeTracker.Repository.EmployeeRepository;
import com.example.TimeTracker.Service.Interfaces.EmployeeServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements EmployeeServiceInterface {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    // Manual constructor injection
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper,PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder=passwordEncoder;
    }

    /*public EmployeeResponseDTO registerEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Optional<Employee> existedUser=employeeRepository.findByUsername(employee.getUsername());
        if(existedUser.isPresent()){
            throw new ResourceNotFoundException("Username already exists");
        }
        System.out.println(employeeDTO.getUsername());
        employee.setUsername(employeeDTO.getUsername());
        employee.setDepartment(employeeDTO.getDepartment());
        eloyee.setEmail(employeeDTO.getEmail());
        Employee saved = employeeRepository.save(employee);
        return modelMapper.map(saved, EmployeeResponseDTO.class);
    }*/

    public void deleteEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with ID: " + id);
        }
        employeeRepository.deleteById(id); // Cascades deletion of PunchEntries
    }


}
