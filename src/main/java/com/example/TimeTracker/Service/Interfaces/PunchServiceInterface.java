package com.example.TimeTracker.Service.Interfaces;

import com.example.TimeTracker.DTO.PunchDurationResponseDTO;
import com.example.TimeTracker.DTO.PunchHistoryRequestDTO;
import com.example.TimeTracker.DTO.PunchHistoryResponseDTO;
import com.example.TimeTracker.DTO.PunchRequestDTO;

import java.time.LocalDate;

public interface PunchServiceInterface {
    void recordPunch(PunchRequestDTO punchRequestDTO);
    PunchDurationResponseDTO calculateTotalWorkedDuration(Long ssid, LocalDate date);
    PunchHistoryResponseDTO getPunchHistory(PunchHistoryRequestDTO requestDTO, int page, int size);

}
