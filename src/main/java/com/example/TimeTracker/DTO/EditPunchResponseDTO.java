package com.example.TimeTracker.DTO;

import java.util.List;

public class EditPunchResponseDTO {
    private Long employeeId;
    private String date;
    private String status;
    private List<EditPunchFieldRequestDTO.PunchDTO> updatedPunches;
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<EditPunchFieldRequestDTO.PunchDTO> getUpdatedPunches() {
        return updatedPunches;
    }
    public void setUpdatedPunches(List<EditPunchFieldRequestDTO.PunchDTO> updatedPunches) {
        this.updatedPunches = updatedPunches;
    }
}

