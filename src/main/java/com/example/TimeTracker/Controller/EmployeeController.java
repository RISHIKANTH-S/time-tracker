package com.example.TimeTracker.Controller;

import com.example.TimeTracker.DTO.EmployeeDTO;
import com.example.TimeTracker.Service.Impl.EmployeeService;
import com.example.TimeTracker.Service.Interfaces.EmployeeServiceInterface;
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
    @PostMapping("/register")
    public ResponseEntity<EmployeeDTO> registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployee = employeeServiceInterface.registerEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
            employeeServiceInterface.deleteEmployeeById(id);
            return ResponseEntity.ok("Employee of "+id+" deleted.");
    }
}
