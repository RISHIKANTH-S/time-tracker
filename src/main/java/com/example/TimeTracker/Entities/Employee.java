package com.example.TimeTracker.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    private String department;
    private String designation;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL, orphanRemoval = true) // "employee" refers to the field in PunchEntry that owns the relationship
    private List<PunchEntry> punchEntries = new ArrayList<>();
    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getDesignation() {
        return designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getOrgNo() {
        return orgNo;
    }
    public void setOrgNo(Long orgNo) {
        this.orgNo = orgNo;
    }

    public List<PunchEntry> getPunchEntries() {
        return punchEntries;
    }
    public void setPunchEntries(List<PunchEntry> punchEntries) {
        this.punchEntries = punchEntries;
    }
}
