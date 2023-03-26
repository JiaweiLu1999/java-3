package com.example.homework.day18.service.impl;

import com.example.homework.day18.domain.dto.StudentResponseDTO;
import com.example.homework.day18.domain.dto.TeacherRequestDTO;
import com.example.homework.day18.domain.dto.TeacherResponseDTO;
import com.example.homework.day18.domain.entity.Student_Teacher;
import com.example.homework.day18.domain.entity.Teacher;
import com.example.homework.day18.repository.TeacherRepository;
import com.example.homework.day18.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private static final Logger logger = LogManager.getLogger(TeacherServiceImpl.class);

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public ResponseEntity<TeacherResponseDTO> getById(String id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isPresent()) {
            logger.info("Found teacher: " + teacher.get());
            TeacherResponseDTO teacherResponseDTO = new TeacherResponseDTO(teacher.get());
            return new ResponseEntity<>(teacherResponseDTO, HttpStatus.OK);
        } else {
            logger.info("Teacher id=" + id + " is not in the database.");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Collection<TeacherResponseDTO>> getAll() {
        Collection<TeacherResponseDTO> res = new ArrayList<>();
        for (Teacher teacher : teacherRepository.findAll()) {
            res.add(new TeacherResponseDTO(teacher));
        }
        logger.info("Found All Teachers: size=" + res.size());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> create(TeacherRequestDTO requestDTO) {
        Teacher t = teacherRepository.save(new Teacher(requestDTO.getName()));
        logger.info("Create Teacher:" + t);
        return new ResponseEntity<>(t.getId(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(TeacherRequestDTO requestDTO) {
        Optional<Teacher> teacher = teacherRepository.findById(requestDTO.getId());
        if (teacher.isEmpty()) {
            logger.info("Cannot update teacher id=" + requestDTO.getId() + ": Not Exist.");
            return new ResponseEntity<>("No entry id=" + requestDTO.getId(), HttpStatus.NOT_FOUND);
        } else {
            Teacher t = new Teacher(requestDTO.getId(), requestDTO.getName());
            logger.info("Update student:" + teacher.get() + "->" + t);
            teacherRepository.save(t);
            return new ResponseEntity<>(t.getId(), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<Collection<StudentResponseDTO>> getStudents(String id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            List<StudentResponseDTO> res = teacher.map(s -> s.getStudents()
                            .stream()
                            .map(Student_Teacher::getStudent).map(StudentResponseDTO::new)
                            .collect(Collectors.toList()))
                    .get();
            logger.info("Found Students of Teacher id=" + id + ", # of students:" + res.size());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            logger.info("Cannot find Students of Teacher id=" + id + ": Teacher Not Exist");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
