package com.example.TimeTracker.Service.Interfaces;

import com.example.TimeTracker.DTO.EmployeeDTO;
import com.example.TimeTracker.DTO.EmployeeResponseDTO;
import com.example.TimeTracker.DTO.UserRegistrationRequestDTO;

public interface UsersServiceInterface {
    String registerUser(UserRegistrationRequestDTO userRegistrationRequestDTO);
}
