package com.example.homework.day18.domain.dto;


import com.example.homework.day18.domain.entity.Teacher;
import lombok.Data;

@Data
public class TeacherResponseDTO {
    private String id;
    private String name;

    public TeacherResponseDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
    }
}
