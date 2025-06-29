package com.example.TimeTracker.Scheduler;

import com.example.TimeTracker.Entities.Employee;
import com.example.TimeTracker.Entities.PunchEntry;
import com.example.TimeTracker.Entities.Users;
import com.example.TimeTracker.Enums.PunchType;
import com.example.TimeTracker.Repository.EmployeeRepository;
import com.example.TimeTracker.Repository.PunchEntryRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReminderScheduler {
    private final PunchEntryRepository punchEntryRepository;
    private final EmployeeRepository employeeRepository;
    private final NotificationService notificationService;

    public ReminderScheduler(PunchEntryRepository punchEntryRepository,
                             EmployeeRepository employeeRepository,
                             NotificationService notificationService) {
        this.punchEntryRepository = punchEntryRepository;
        this.employeeRepository = employeeRepository;
        this.notificationService = notificationService;
    }

    /***@Scheduled(cron = "0 50 18 * * *") // Run at 8 PM every day to check exit punch
    public void sendPunchOutReminders() {
        LocalDate today = LocalDate.now();
        List<Employee> employees = employeeRepository.findAll();

        for (Users user : employees) {
            Optional<PunchEntry> lastPunch = punchEntryRepository
                    .findLastPunchOfDay(employee.getOrgNo(), today);

            if (lastPunch.isPresent() && lastPunch.get().getPunchType() == PunchType.IN) {
                notificationService.sendReminder(employee);
            }
        }
    }***/
}
