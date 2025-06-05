package com.example.TimeTracker.Service.Interfaces;

import com.example.TimeTracker.DTO.EmployeeDTO;

public interface EmployeeServiceInterface {
    EmployeeDTO registerEmployee(EmployeeDTO employeeDTO);

    void deleteEmployeeById(Long id);
}
