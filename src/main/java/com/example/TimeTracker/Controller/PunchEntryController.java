package com.example.TimeTracker.Controller;

import com.example.TimeTracker.DTO.*;
import com.example.TimeTracker.Service.Impl.PunchService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/punches")
public class PunchEntryController {
    private final PunchService punchService;
    public PunchEntryController(PunchService punchService) {
        this.punchService = punchService;
    }
    @PostMapping("/record")
    public ResponseEntity<String> recordPunch(@RequestBody PunchRequestDTO punchRequestDTO) {
        punchService.recordPunch(punchRequestDTO);
        return ResponseEntity.ok("Punch recorded successfully.");
    }
    @GetMapping("/duration")
    public ResponseEntity<PunchDurationResponseDTO> getWorkedDuration(@RequestParam Long ssid,
                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        PunchDurationResponseDTO response = punchService.calculateTotalWorkedDuration(ssid,date);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/history")
    public ResponseEntity<PunchHistoryResponseDTO> getPunchHistory(@RequestBody PunchHistoryRequestDTO requestDTO,@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "3") int size) {
        PunchHistoryResponseDTO response = punchService.getPunchHistory(requestDTO,page,size);
        return ResponseEntity.ok(response);
    }

}
