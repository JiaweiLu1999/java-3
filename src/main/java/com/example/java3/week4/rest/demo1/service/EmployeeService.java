package com.example.java3.week4.rest.demo1.service;

import com.example.java3.week4.rest.demo1.domain.dto.EmployeeResponseDTO;
import com.example.java3.week4.rest.demo1.domain.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface EmployeeService {
    EmployeeResponseDTO findById(int id);
    Collection<EmployeeResponseDTO> findAll();
    int insert(Employee emp);
}
