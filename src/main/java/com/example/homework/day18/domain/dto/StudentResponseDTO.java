package com.example.homework.day18.domain.dto;

import com.example.homework.day18.domain.entity.Student;
import lombok.Data;

@Data
public class StudentResponseDTO {
    private String id;
    private String name;

    public StudentResponseDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
    }
}
