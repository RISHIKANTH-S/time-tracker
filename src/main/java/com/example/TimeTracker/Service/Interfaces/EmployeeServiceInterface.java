package com.example.TimeTracker.Service.Interfaces;

import com.example.TimeTracker.DTO.EmployeeDTO;
import com.example.TimeTracker.DTO.EmployeeResponseDTO;

public interface EmployeeServiceInterface {

    //EmployeeResponseDTO registerEmployee(EmployeeDTO employeeDTO);

    void deleteEmployeeById(Long id);
}
