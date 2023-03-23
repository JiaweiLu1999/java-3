package com.example.java3.week4.rest.demo1.service;

import com.example.java3.week4.rest.demo1.domain.dto.EmployeeResponseDTO;
import com.example.java3.week4.rest.demo1.domain.entity.Employee;
import com.example.java3.week4.rest.demo1.exception.ResourceNotFoundException;
import com.example.java3.week4.rest.demo1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDTO findById(int id) {
        Employee employee = employeeRepository.findById(id);
        //log info
        if(employee == null) {
            //log error
            throw new ResourceNotFoundException(".....");
        }
        return new EmployeeResponseDTO(employee);
    }

    @Override
    public Collection<EmployeeResponseDTO> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(e -> new EmployeeResponseDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public int insert(Employee emp) {
        return employeeRepository.insert(emp);
    }
}
