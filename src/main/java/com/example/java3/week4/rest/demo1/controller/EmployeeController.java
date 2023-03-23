package com.example.java3.week4.rest.demo1.controller;

import com.example.java3.week4.rest.demo1.domain.entity.Employee;
import com.example.java3.week4.rest.demo1.exception.ExceptionResponseDTO;
import com.example.java3.week4.rest.demo1.exception.ResourceNotFoundException;
import com.example.java3.week4.rest.demo1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

//@Controller // model and view + view resolver
//@ResponseBody // http message converter + jackson
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllEmp() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpById(@PathVariable int id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> getEmpById(@RequestBody Employee emp) {
        return new ResponseEntity<>(employeeService.insert(emp), HttpStatus.CREATED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleException() {
        return new ResponseEntity<>(new ExceptionResponseDTO("cannot find this resource"), HttpStatus.NOT_FOUND);
    }
}
