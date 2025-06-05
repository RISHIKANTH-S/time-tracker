package com.example.TimeTracker.Service.Impl;

import com.example.TimeTracker.DTO.EditPunchFieldRequestDTO;
import com.example.TimeTracker.DTO.EditPunchResponseDTO;
import com.example.TimeTracker.Entities.Employee;
import com.example.TimeTracker.Entities.PunchEntry;
import com.example.TimeTracker.Repository.EmployeeRepository;
import com.example.TimeTracker.Repository.PunchEntryRepository;
import com.example.TimeTracker.Service.Interfaces.AdminServiceInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService implements AdminServiceInterface {
    private final EmployeeRepository employeeRepository;
    private final PunchEntryRepository punchEntryRepository;
    public AdminService(EmployeeRepository employeeRepository, PunchEntryRepository punchEntryRepository) {
        this.employeeRepository = employeeRepository;
        this.punchEntryRepository = punchEntryRepository;
    }
    public EditPunchResponseDTO editPunch(EditPunchFieldRequestDTO requestDTO) {
        Long employeeId = requestDTO.getEmployeeId();
        LocalDate date = LocalDate.parse(requestDTO.getDate());
        // Step 1: Delete existing punches
        punchEntryRepository.deleteByEmployeeAndDate(employeeId, date);
        // Step 2: Fetch employee
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        // Step 3: Save new punches
        List<PunchEntry> newEntries = requestDTO.getNewPunches().stream().map(dto -> {
            PunchEntry entry = new PunchEntry();
            entry.setEmployee(employee);
            entry.setPunchDate(date);
            entry.setPunchTime(dto.getTime());
            entry.setPunchType(dto.getType());
            return entry;
        }).collect(Collectors.toList());
        punchEntryRepository.saveAll(newEntries);

        EditPunchResponseDTO responseDTO = new EditPunchResponseDTO();
        responseDTO.setEmployeeId(employeeId);
        responseDTO.setDate(date.toString());
        responseDTO.setStatus("Punch updated successfully.");
        responseDTO.setUpdatedPunches(requestDTO.getNewPunches());

        return responseDTO;
    }
}
