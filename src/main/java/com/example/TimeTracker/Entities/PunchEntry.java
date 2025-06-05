package com.example.TimeTracker.Entities;

import com.example.TimeTracker.Enums.PunchType;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class PunchEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PunchType punchType;

    private LocalDate punchDate;

    private LocalTime punchTime;

    @ManyToOne
    @JoinColumn(name = "employee_ssid")
    private Employee employee;
    public PunchEntry() {
    }

    public PunchEntry(Long id, PunchType punchType, LocalDate punchDate, LocalTime punchTime, Employee employee) {
        this.id = id;
        this.punchType = punchType;
        this.punchDate = punchDate;
        this.punchTime = punchTime;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PunchType getPunchType() {
        return punchType;
    }

    public void setPunchType(PunchType punchType) {
        this.punchType = punchType;
    }

    public LocalDate getPunchDate() {
        return punchDate;
    }

    public void setPunchDate(LocalDate punchDate) {
        this.punchDate = punchDate;
    }

    public LocalTime getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(LocalTime punchTime) {
        this.punchTime = punchTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
