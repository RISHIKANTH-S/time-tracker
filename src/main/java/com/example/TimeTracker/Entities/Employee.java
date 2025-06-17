package com.example.TimeTracker.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orgNo;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private String department;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL, orphanRemoval = true) // "employee" refers to the field in PunchEntry that owns the relationship
    private List<PunchEntry> punchEntries = new ArrayList<>();
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getOrgNo() {
        return orgNo;
    }
    public void setOrgNo(Long orgNo) {
        this.orgNo = orgNo;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public List<PunchEntry> getPunchEntries() {
        return punchEntries;
    }
    public void setPunchEntries(List<PunchEntry> punchEntries) {
        this.punchEntries = punchEntries;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
