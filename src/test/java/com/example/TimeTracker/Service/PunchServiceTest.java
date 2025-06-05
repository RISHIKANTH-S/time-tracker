package com.example.TimeTracker.Service;

import com.example.TimeTracker.DTO.PunchDurationResponseDTO;
import com.example.TimeTracker.DTO.PunchRequestDTO;
import com.example.TimeTracker.Entities.Employee;
import com.example.TimeTracker.Entities.PunchEntry;
import com.example.TimeTracker.Enums.PunchType;
import com.example.TimeTracker.Exceptions.ResourceNotFoundException;
import com.example.TimeTracker.Repository.EmployeeRepository;
import com.example.TimeTracker.Repository.PunchEntryRepository;
import com.example.TimeTracker.Service.Impl.PunchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PunchServiceTest {
    @InjectMocks
    private PunchService punchService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PunchEntryRepository punchEntryRepository;

    private Employee dummyEmployee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dummyEmployee = new Employee();
        dummyEmployee.setOrgNo(1L);
        dummyEmployee.setName("Rishi");
    }

    @Test
    void testRecordPunch_FirstPunchIsIN_ShouldSucceed() {
        PunchRequestDTO dto = new PunchRequestDTO();
        dto.setSsid(1L);
        dto.setPunchType(PunchType.IN);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(dummyEmployee));
        when(punchEntryRepository.findLatestPunchByEmployee(1L)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> punchService.recordPunch(dto));
        verify(punchEntryRepository, times(1)).save(any(PunchEntry.class));
    }

    @Test
    void testRecordPunch_FirstPunchIsOUT_ShouldThrowException() {
        PunchRequestDTO dto = new PunchRequestDTO();
        dto.setSsid(1L);
        dto.setPunchType(PunchType.OUT);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(dummyEmployee));
        when(punchEntryRepository.findLatestPunchByEmployee(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> punchService.recordPunch(dto));

        assertEquals("First punch must be IN.", ex.getMessage());
        verify(punchEntryRepository, never()).save(any());
    }

    @Test
    void testRecordPunch_ConsecutiveSamePunch_ShouldThrowException() {
        PunchRequestDTO dto = new PunchRequestDTO();
        dto.setSsid(1L);
        dto.setPunchType(PunchType.IN);

        PunchEntry lastPunch = new PunchEntry();
        lastPunch.setPunchType(PunchType.IN);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(dummyEmployee));
        when(punchEntryRepository.findLatestPunchByEmployee(1L)).thenReturn(Optional.of(lastPunch));

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> punchService.recordPunch(dto));

        assertEquals("Cannot have two consecutive punches of type: IN", ex.getMessage());
        verify(punchEntryRepository, never()).save(any());
    }

    @Test
    void testRecordPunch_AlternatingPunch_ShouldSucceed() {
        PunchRequestDTO dto = new PunchRequestDTO();
        dto.setSsid(1L);
        dto.setPunchType(PunchType.IN);

        PunchEntry lastPunch = new PunchEntry();
        lastPunch.setPunchType(PunchType.OUT);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(dummyEmployee));
        when(punchEntryRepository.findLatestPunchByEmployee(1L)).thenReturn(Optional.of(lastPunch));

        assertDoesNotThrow(() -> punchService.recordPunch(dto));
        verify(punchEntryRepository, times(1)).save(any(PunchEntry.class));
    }
    /***@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }***/

    @Test
    void testCalculateTotalWorkedDuration_WithValidInOutPairs() {
        // Arrange
        Long ssid = 1L;
        LocalDate date = LocalDate.of(2025, 5, 6);

        Employee employee = new Employee();
        employee.setOrgNo(ssid);

        PunchEntry in1 = new PunchEntry();
        in1.setEmployee(employee);
        in1.setPunchDate(date);
        in1.setPunchTime(LocalTime.of(9, 0));
        in1.setPunchType(PunchType.IN);

        PunchEntry out1 = new PunchEntry();
        out1.setEmployee(employee);
        out1.setPunchDate(date);
        out1.setPunchTime(LocalTime.of(12, 0));
        out1.setPunchType(PunchType.OUT);

        PunchEntry in2 = new PunchEntry();
        in2.setEmployee(employee);
        in2.setPunchDate(date);
        in2.setPunchTime(LocalTime.of(13, 0));
        in2.setPunchType(PunchType.IN);

        PunchEntry out2 = new PunchEntry();
        out2.setEmployee(employee);
        out2.setPunchDate(date);
        out2.setPunchTime(LocalTime.of(17, 30));
        out2.setPunchType(PunchType.OUT);

        when(punchEntryRepository.findPunchesByOrgNoAndDate(ssid, date))
                .thenReturn(List.of(in1, out1, in2, out2));

        // Act
        PunchDurationResponseDTO result = punchService.calculateTotalWorkedDuration(ssid, date);

        // Assert
        assertEquals("2025-05-06", result.getDate());
        assertEquals(ssid, result.getSsid());
        assertEquals("7 hours and 30 minutes", result.getDuration());
    }
}
