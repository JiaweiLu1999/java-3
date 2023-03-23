package com.example.java3.week4.rest.demo1.repository;

import com.example.java3.week4.rest.demo1.domain.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final Map<Integer, Employee> cache = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(0);

    @Override
    public Employee findById(int id) {
        return cache.get(id);
    }

    @Override
    public Collection<Employee> findAll() {
        return cache.values();
    }

    @Override
    public int insert(Employee emp) {
        int emp_id = id.getAndIncrement();
        emp.setId(emp_id);
        emp.setActive(true);
        emp.setCreatedTime(new Date());
        cache.put(emp_id, emp);
        return emp_id;
    }
}
