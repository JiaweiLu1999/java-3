package com.example.homework.day18.repository;

import com.example.homework.day18.domain.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, String>{

}
