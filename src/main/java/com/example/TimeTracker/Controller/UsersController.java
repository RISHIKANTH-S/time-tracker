package com.example.TimeTracker.Controller;

import com.example.TimeTracker.DTO.EmployeeDTO;
import com.example.TimeTracker.DTO.EmployeeResponseDTO;
import com.example.TimeTracker.DTO.UserRegistrationRequestDTO;
import com.example.TimeTracker.Service.Interfaces.UsersServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UsersController {
    private final UsersServiceInterface usersServiceInterface;
    public UsersController(UsersServiceInterface usersServiceInterface){
        this.usersServiceInterface=usersServiceInterface;
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee(@RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO) {
        String savedEmployee = usersServiceInterface.registerUser(userRegistrationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }
}
