package com.example.homework.day18.service;

import com.example.homework.day18.domain.dto.StudentRequestDTO;
import com.example.homework.day18.domain.dto.StudentResponseDTO;
import com.example.homework.day18.domain.dto.TeacherResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface StudentService {
    ResponseEntity<StudentResponseDTO> getById(String id);
    ResponseEntity<Collection<StudentResponseDTO>> getAll();
    ResponseEntity<String> create(StudentRequestDTO requestDTO);
    ResponseEntity<String> update(StudentRequestDTO requestDTO);
    ResponseEntity<Collection<TeacherResponseDTO>> getTeachers(String id);
}
