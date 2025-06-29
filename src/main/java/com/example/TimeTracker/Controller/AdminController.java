package com.example.TimeTracker.Controller;

import com.example.TimeTracker.DTO.EditPunchFieldRequestDTO;
import com.example.TimeTracker.DTO.EditPunchResponseDTO;
import com.example.TimeTracker.Service.Impl.AdminService;
import com.example.TimeTracker.Service.Interfaces.AdminServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminServiceInterface adminServiceInterface;

    public AdminController(AdminServiceInterface adminServiceInterface) {
        this.adminServiceInterface = adminServiceInterface;
    }

    @PutMapping("/edit-punch")
    public ResponseEntity<EditPunchResponseDTO> editEmployeePunch(@RequestBody EditPunchFieldRequestDTO requestDTO) {
        EditPunchResponseDTO response=adminServiceInterface.editPunch(requestDTO);
        return ResponseEntity.ok(response);
    }
}
