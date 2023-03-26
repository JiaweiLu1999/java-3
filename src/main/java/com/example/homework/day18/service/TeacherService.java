package com.example.homework.day18.service;

import com.example.homework.day18.domain.dto.StudentResponseDTO;
import com.example.homework.day18.domain.dto.TeacherRequestDTO;
import com.example.homework.day18.domain.dto.TeacherResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface TeacherService {
    ResponseEntity<Collection<TeacherResponseDTO>> getAll();
    ResponseEntity<TeacherResponseDTO> getById(String id);
    ResponseEntity<String> create(TeacherRequestDTO requestDTO);
    ResponseEntity<String> update(TeacherRequestDTO requestDTO);
    ResponseEntity<Collection<StudentResponseDTO>> getStudents(String id);
}
