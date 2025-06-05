package com.example.TimeTracker.Service.Interfaces;

import com.example.TimeTracker.DTO.EditPunchFieldRequestDTO;
import com.example.TimeTracker.DTO.EditPunchResponseDTO;

public interface AdminServiceInterface {
    EditPunchResponseDTO editPunch(EditPunchFieldRequestDTO requestDTO);
}
