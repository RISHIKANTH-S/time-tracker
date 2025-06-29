package com.example.TimeTracker.Repository;

import com.example.TimeTracker.Entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {

}
