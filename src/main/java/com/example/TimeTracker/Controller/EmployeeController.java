package com.example.TimeTracker.Controller;

import com.example.TimeTracker.DTO.EmployeeDTO;
import com.example.TimeTracker.DTO.EmployeeResponseDTO;
import com.example.TimeTracker.Service.Impl.EmployeeService;
import com.example.TimeTracker.Service.Interfaces.EmployeeServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeServiceInterface employeeServiceInterface;

    public EmployeeController(EmployeeServiceInterface employeeServiceInterface) {
        this.employeeServiceInterface = employeeServiceInterface;
    }
    /*@PostMapping("/register")
    public ResponseEntity<EmployeeResponseDTO> registerEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeResponseDTO savedEmployee = employeeServiceInterface.registerEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }*/
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
            employeeServiceInterface.deleteEmployeeById(id);
            return ResponseEntity.ok("Employee of "+id+" deleted.");
    }
}
