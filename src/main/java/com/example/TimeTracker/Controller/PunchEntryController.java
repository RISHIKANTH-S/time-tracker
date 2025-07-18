package com.example.TimeTracker.Controller;

import com.example.TimeTracker.DTO.*;
import com.example.TimeTracker.Service.Impl.PunchService;
import com.example.TimeTracker.Service.Interfaces.PunchServiceInterface;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/punches")
public class PunchEntryController {
    private final PunchServiceInterface punchServiceInterface;
    public PunchEntryController(PunchServiceInterface punchServiceInterface) {
        this.punchServiceInterface = punchServiceInterface;
    }
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping("/record")
    public ResponseEntity<String> recordPunch(@RequestBody PunchRequestDTO punchRequestDTO) {
        punchServiceInterface.recordPunch(punchRequestDTO);
        return ResponseEntity.ok("Punch recorded successfully.");
    }
    @GetMapping("/duration")
    public ResponseEntity<PunchDurationResponseDTO> getWorkedDuration(@RequestParam Long ssid,
                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        PunchDurationResponseDTO response = punchServiceInterface.calculateTotalWorkedDuration(ssid,date);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/history")
    public ResponseEntity<PunchHistoryResponseDTO> getPunchHistory(@RequestBody PunchHistoryRequestDTO requestDTO,@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "3") int size) {
        PunchHistoryResponseDTO response = punchServiceInterface.getPunchHistory(requestDTO,page,size);
        return ResponseEntity.ok(response);
    }

}
