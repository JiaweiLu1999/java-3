package com.example.java3;

import static org.junit.jupiter.api.Assertions.*;

import com.example.java3.week4.rest.demo1.domain.dto.EmployeeResponseDTO;
import com.example.java3.week4.rest.demo1.domain.entity.Employee;
import com.example.java3.week4.rest.demo1.exception.ResourceNotFoundException;
import com.example.java3.week4.rest.demo1.repository.EmployeeRepository;
import com.example.java3.week4.rest.demo1.service.EmployeeService;
import com.example.java3.week4.rest.demo1.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

public class MyUnitTest {

    private static EmployeeService service;
    private static EmployeeRepository repository;

    @BeforeAll
    public static void init() {
        repository = mock(EmployeeRepository.class);
        when(repository.findById(5)).thenReturn(null);
        when(repository.findById(1)).thenReturn(new Employee(1));
        service = new EmployeeServiceImpl(repository);
    }

    @Test
    public void testEmployeeNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> service.findById(5));
    }


    @Test
    public void testEmployeeData() {
        EmployeeResponseDTO expectedData = new EmployeeResponseDTO();
        expectedData.setId(1);
        assertEquals(service.findById(1), expectedData);
    }

}
