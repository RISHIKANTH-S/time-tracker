package com.example.TimeTracker.Service.Impl;

import com.example.TimeTracker.DTO.*;
import com.example.TimeTracker.Entities.Employee;
import com.example.TimeTracker.Entities.PunchEntry;
import com.example.TimeTracker.Entities.Users;
import com.example.TimeTracker.Enums.PunchType;
import com.example.TimeTracker.Exceptions.ResourceNotFoundException;
import com.example.TimeTracker.Repository.EmployeeRepository;
import com.example.TimeTracker.Repository.PunchEntryRepository;
import com.example.TimeTracker.Repository.UsersRepository;
import com.example.TimeTracker.Service.Interfaces.PunchServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PunchService implements PunchServiceInterface {
    private final UsersRepository usersRepository;
    private final EmployeeRepository employeeRepository;
    private final PunchEntryRepository punchEntryRepository;
    public PunchService(UsersRepository usersRepository,PunchEntryRepository punchEntryRepository, EmployeeRepository employeeRepository) {
        this.punchEntryRepository = punchEntryRepository;
        this.employeeRepository = employeeRepository;
        this.usersRepository=usersRepository;
    }
    public void recordPunch(PunchRequestDTO punchRequestDTO) {
        Long ssid = punchRequestDTO.getSsid();
        PunchType currentPunch = punchRequestDTO.getPunchType();
        Employee employee = employeeRepository.findByUserId(ssid)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with SSID: " + ssid));
        // Fetch the last punch (by date and time)
        Optional<PunchEntry> lastPunchOpt = punchEntryRepository.findLatestPunchByEmployee(ssid);
        // If no punch exists, the first punch must be IN
        if (lastPunchOpt.isEmpty()) {
            if (currentPunch != PunchType.IN) {
                throw new ResourceNotFoundException("First punch must be IN.");
            }
        }
        else {
            PunchType lastPunchType = lastPunchOpt.get().getPunchType();
            if (lastPunchType == currentPunch) {
                throw new ResourceNotFoundException("Cannot have two consecutive punches of type: " + currentPunch);
            }
        }
        PunchEntry punchEntry = new PunchEntry();
        punchEntry.setEmployee(employee);
        punchEntry.setPunchType(currentPunch);
        punchEntry.setPunchDate(LocalDate.now());
        punchEntry.setPunchTime(LocalTime.now());
        punchEntryRepository.save(punchEntry);
    }
    public PunchDurationResponseDTO calculateTotalWorkedDuration(Long ssid,LocalDate date) {
        List<PunchEntry> punches = punchEntryRepository.findPunchesByOrgNoAndDate(ssid,date);
        Duration totalDuration = Duration.ZERO;
        LocalTime inTime = null;
        for (PunchEntry punch : punches) {
            if (punch.getPunchType() == PunchType.IN) {
                inTime = punch.getPunchTime();
            } else if (punch.getPunchType() == PunchType.OUT && inTime != null) {
                Duration session = Duration.between(inTime, punch.getPunchTime());
                totalDuration = totalDuration.plus(session);
                inTime = null;
            }
        }
        long hours = totalDuration.toHours();
        long minutes = totalDuration.minusHours(hours).toMinutes();
        String formattedDuration = String.format("%d hours and %d minutes", hours, minutes);
        return new PunchDurationResponseDTO(date.toString(),ssid, formattedDuration);
    }
    public PunchHistoryResponseDTO getPunchHistory(PunchHistoryRequestDTO requestDTO,int page,int size) {
        LocalDate start = LocalDate.parse(requestDTO.getStartDate());
        LocalDate end = LocalDate.parse(requestDTO.getEndDate());
        Pageable pageable = PageRequest.of(page, size);

        Page<PunchEntry> entries = punchEntryRepository
                .findPunchHistory(
                        requestDTO.getSsid(), start, end,pageable);
        List<PunchHistoryResponseDTO.PunchRecord> records = entries.stream().map(entry -> {
            PunchHistoryResponseDTO.PunchRecord record = new PunchHistoryResponseDTO.PunchRecord();
            record.setDate(entry.getPunchDate().toString());
            record.setTime(entry.getPunchTime().toString());
            record.setType(entry.getPunchType());
            return record;
        }).collect(Collectors.toList());

        PunchHistoryResponseDTO response = new PunchHistoryResponseDTO();
        response.setSsid(requestDTO.getSsid());
        response.setRecords(records);
        return response;
    }

}
