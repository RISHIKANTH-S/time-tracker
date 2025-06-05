package com.example.TimeTracker.DTO;

import com.example.TimeTracker.Enums.PunchType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PunchRequestDTO {
    private Long ssid;
    private PunchType punchType; // IN / OUT

    public Long getSsid() {
        return ssid;
    }
    public void setSsid(Long ssid) {
        this.ssid = ssid;
    }
    public PunchType getPunchType() {
        return punchType;
    }
    public void setPunchType(PunchType punchType) {
        this.punchType = punchType;
    }
}
