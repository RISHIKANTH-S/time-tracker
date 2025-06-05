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
    private String name;
    private LocalDateTime createdAt;
    private String department;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL, orphanRemoval = true) // "employee" refers to the field in PunchEntry that owns the relationship
    private List<PunchEntry> punchEntries = new ArrayList<>();
    private String email;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
