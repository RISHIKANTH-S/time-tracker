package com.example.TimeTracker.DTO;

import java.time.Duration;

public class PunchDurationResponseDTO {
        private String date;
        private Long ssid;
        private String duration; // e.g., "2 hours and 30 minutes"
        public String getDate() {
            return date;
        }
    public PunchDurationResponseDTO(String date, Long ssid, String duration) {
        this.date = date;
        this.ssid = ssid;
        this.duration = duration;
    }
    public void setDate(String date) {
            this.date = date;
        }

        public Long getSsid() {
            return ssid;
        }

        public void setSsid(Long ssid) {
            this.ssid = ssid;
        }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
