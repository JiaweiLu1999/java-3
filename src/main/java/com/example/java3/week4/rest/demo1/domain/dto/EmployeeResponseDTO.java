package com.example.java3.week4.rest.demo1.domain.dto;

import com.example.java3.week4.rest.demo1.domain.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeResponseDTO {
    private int id;
    private String name;
    private int age;

    public EmployeeResponseDTO(Employee emp) {
        id = emp.getId();
        name = emp.getName();
        age = emp.getAge();
    }
}
