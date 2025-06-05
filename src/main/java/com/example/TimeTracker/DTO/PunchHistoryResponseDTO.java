package com.example.TimeTracker.DTO;

import com.example.TimeTracker.Enums.PunchType;

import java.util.List;

public class PunchHistoryResponseDTO {
    private Long ssid;
    private List<PunchRecord> records;

    public Long getSsid() {
        return ssid;
    }

    public void setSsid(Long ssid) {
        this.ssid = ssid;
    }

    public List<PunchRecord> getRecords() {
        return records;
    }

    public void setRecords(List<PunchRecord> records) {
        this.records = records;
    }

    public static class PunchRecord {
        private String date;     // yyyy-MM-dd
        private String time;     // HH:mm:ss
        private PunchType type;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
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
