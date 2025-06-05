package com.example.TimeTracker.Controller;

import com.example.TimeTracker.DTO.EditPunchFieldRequestDTO;
import com.example.TimeTracker.DTO.EditPunchResponseDTO;
import com.example.TimeTracker.Service.Impl.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PutMapping("/edit-punch")
    public ResponseEntity<EditPunchResponseDTO> editEmployeePunch(@RequestBody EditPunchFieldRequestDTO requestDTO) {
        EditPunchResponseDTO response=adminService.editPunch(requestDTO);
        return ResponseEntity.ok(response);
    }
}
