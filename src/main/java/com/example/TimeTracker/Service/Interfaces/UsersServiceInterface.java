package com.example.TimeTracker.Service.Interfaces;

import com.example.TimeTracker.DTO.UserRegistrationRequestDTO;

public interface UsersServiceInterface {
    String registerUser(UserRegistrationRequestDTO userRegistrationRequestDTO);
}
