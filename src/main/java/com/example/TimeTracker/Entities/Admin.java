package com.example.TimeTracker.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String department;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();
}
