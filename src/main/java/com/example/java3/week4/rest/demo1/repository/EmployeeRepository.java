package com.example.java3.week4.rest.demo1.repository;

import com.example.java3.week4.rest.demo1.domain.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EmployeeRepository {
    Employee findById(int id);
    Collection<Employee> findAll();
    int insert(Employee emp);
}
