package com.example.TimeTracker.Repository;
import com.example.TimeTracker.Entities.PunchEntry;
//import org.hibernate.query.Page;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface PunchEntryRepository extends JpaRepository<PunchEntry, Long> {
        @Query(
                value = "SELECT * FROM punch_entry " +
                        "WHERE employee_ssid = :orgNo " +
                        "ORDER BY punch_date DESC, punch_time DESC " +
                        "LIMIT 1",
                nativeQuery = true
        )
        Optional<PunchEntry> findLatestPunchByEmployee(@Param("orgNo") Long orgNo);
        @Query(value = "SELECT * FROM punch_entry WHERE employee_ssid = :orgNo AND punch_date = :date ORDER BY punch_time ASC", nativeQuery = true)
        List<PunchEntry> findPunchesByOrgNoAndDate(@Param("orgNo") Long orgNo, @Param("date") LocalDate date);
        @Query(value = "SELECT * FROM punch_entry WHERE employee_ssid = :ssid AND punch_date BETWEEN :startDate AND :endDate ORDER BY punch_date ASC, punch_time ASC",
                nativeQuery = true)
        Page<PunchEntry> findPunchHistory(@Param("ssid") Long ssid,
                                          @Param("startDate") LocalDate startDate,
                                          @Param("endDate") LocalDate endDate,Pageable pageable);
        @Query(value = """
            SELECT * FROM punch_entry 
            WHERE employee_ssid = :ssid AND punch_date = :date 
            ORDER BY punch_time DESC 
            LIMIT 1
            """, nativeQuery = true)
        Optional<PunchEntry> findLastPunchOfDay(@Param("ssid") Long ssid, @Param("date") LocalDate date);
        @Modifying
        @Transactional
        @Query("DELETE FROM PunchEntry p WHERE p.employee.orgNo = :employeeId AND p.punchDate = :date")
        void deleteByEmployeeAndDate(@Param("employeeId") Long employeeId, @Param("date") LocalDate date);


}
