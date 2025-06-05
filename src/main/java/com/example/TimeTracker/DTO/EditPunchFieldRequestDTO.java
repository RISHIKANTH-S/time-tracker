package com.example.TimeTracker.DTO;

import com.example.TimeTracker.Enums.PunchType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Data
public class EditPunchFieldRequestDTO {
    private Long employeeId;
    private String date;
    private List<PunchDTO> newPunches;
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
    public List<PunchDTO> getNewPunches() {
        return newPunches;
    }
    public void setNewPunches(List<PunchDTO> newPunches) {
        this.newPunches = newPunches;
    }
    public static class PunchDTO {
        private LocalTime time;
        private PunchType type; // IN or OUT

        public LocalTime getTime() {
            return time;
        }

        public void setTime(LocalTime time) {
            this.time = time;
        }

        public PunchType getType() {
            return type;
        }

        public void setType(PunchType type) {
            this.type = type;
        }
    }

}
