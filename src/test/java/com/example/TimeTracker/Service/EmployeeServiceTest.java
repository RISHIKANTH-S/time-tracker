package com.example.TimeTracker.Service;

import com.example.TimeTracker.Entities.Employee;
import com.example.TimeTracker.Repository.EmployeeRepository;
import com.example.TimeTracker.Service.Impl.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;


public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterEmployee() {
        // Arrange
        EmployeeDTO inputDTO = new EmployeeDTO();
        inputDTO.setName("John Doe");

        Employee mappedEntity = new Employee();
        mappedEntity.setName("John Doe");

        Employee savedEntity = new Employee();
        savedEntity.setOrgNo(1L);
        savedEntity.setName("John Doe");
        savedEntity.setCreatedAt(LocalDateTime.now());

        EmployeeDTO expectedDTO = new EmployeeDTO();
        expectedDTO.setName("John Doe");

        when(modelMapper.map(inputDTO, Employee.class)).thenReturn(mappedEntity);
        when(employeeRepository.save(mappedEntity)).thenReturn(savedEntity);
        when(modelMapper.map(savedEntity, EmployeeDTO.class)).thenReturn(expectedDTO);

        // Act
        EmployeeDTO result = employeeService.registerEmployee(inputDTO);

        // Assert
        //assertEquals(expectedDTO.getId(), result.getId());
        assertEquals(expectedDTO.getName(), result.getName());

        verify(modelMapper).map(inputDTO, Employee.class);
        verify(employeeRepository).save(mappedEntity);
        verify(modelMapper).map(savedEntity, EmployeeDTO.class);
    }
}
