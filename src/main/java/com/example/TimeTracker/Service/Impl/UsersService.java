package com.example.TimeTracker.Service.Impl;

import com.example.TimeTracker.DTO.UserRegistrationRequestDTO;
import com.example.TimeTracker.Entities.Admin;
import com.example.TimeTracker.Entities.Employee;
import com.example.TimeTracker.Entities.Role;
import com.example.TimeTracker.Entities.Users;
import com.example.TimeTracker.Exceptions.ResourceNotFoundException;
import com.example.TimeTracker.Repository.*;
import com.example.TimeTracker.Service.Interfaces.UsersServiceInterface;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
@Service
public class UsersService implements UsersServiceInterface {
    private final UsersRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmployeeRepository employeeRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    public UsersService(UsersRepository userRepository, RoleRepository roleRepository,EmployeeRepository employeeRepository,PasswordEncoder passwordEncoder,AdminRepository adminRepository) {
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.employeeRepository = employeeRepository;
        this.adminRepository=adminRepository;
        this.passwordEncoder=passwordEncoder;
    }

        public String registerUser(UserRegistrationRequestDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Users user = new Users();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());

        Role role = roleRepository.findByName(dto.getRole().toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        user.getRoles().add(role);
        // Save user first so that we get the ID for the profile
        Users savedUser = userRepository.save(user);
        System.out.println(dto.getRole());
        if (dto.getRole().equalsIgnoreCase("ROLE_EMPLOYEE")) {
            Employee employee = new Employee();
            employee.setUser(savedUser);
            employee.setDepartment(dto.getDepartment());
            employee.setDesignation(dto.getDesignation());
            employeeRepository.save(employee);
        } else if (dto.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
            Admin admin = new Admin();
            admin.setUser(savedUser);
            admin.setDepartment(dto.getDepartment());
            adminRepository.save(admin);
        }

        return "User Created";
    }

}
