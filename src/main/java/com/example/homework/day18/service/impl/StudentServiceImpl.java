package com.example.homework.day18.service.impl;

import com.example.homework.day18.domain.dto.StudentRequestDTO;
import com.example.homework.day18.domain.dto.StudentResponseDTO;
import com.example.homework.day18.domain.dto.TeacherResponseDTO;
import com.example.homework.day18.domain.entity.Student;
import com.example.homework.day18.domain.entity.Student_Teacher;
import com.example.homework.day18.repository.StudentRepository;
import com.example.homework.day18.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;




@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public ResponseEntity<StudentResponseDTO> getById(String id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            logger.info("Found student: " + student.get());
            StudentResponseDTO studentResponseDTO = new StudentResponseDTO(student.get());
            return new ResponseEntity<>(studentResponseDTO, HttpStatus.OK);
        } else {
            logger.info("Student id=" + id + " is not in the database.");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Collection<StudentResponseDTO>> getAll() {
        Collection<StudentResponseDTO> res = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            res.add(new StudentResponseDTO(student));
        }
        logger.info("Found All Students: size=" + res.size());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> create(StudentRequestDTO requestDTO) {
        Student s = studentRepository.save(new Student(requestDTO.getName()));
        logger.info("Create student:" + s);
        return new ResponseEntity<>(s.getId(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(StudentRequestDTO requestDTO) {
        Optional<Student> student = studentRepository.findById(requestDTO.getId());
        if (student.isEmpty()) {
            logger.info("Cannot update student id=" + requestDTO.getId() + ": Not Exist.");
            return new ResponseEntity<>("No entry id=" + requestDTO.getId(), HttpStatus.NOT_FOUND);
        } else {
            Student s = new Student(requestDTO.getId(), requestDTO.getName());
            logger.info("Update student:" + student.get() + "->" + s);
            studentRepository.save(s);
            return new ResponseEntity<>(s.getId(), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<Collection<TeacherResponseDTO>> getTeachers(String id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            List<TeacherResponseDTO> res = student.map(s -> s.getTeachers()
                            .stream()
                            .map(Student_Teacher::getTeacher).map(TeacherResponseDTO::new)
                            .collect(Collectors.toList()))
                    .get();
            logger.info("Found Teachers of Student id=" + id + ", # of teachers:" + res.size());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            logger.info("Cannot find Teachers of Student id=" + id + ": Student Not Exist");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
