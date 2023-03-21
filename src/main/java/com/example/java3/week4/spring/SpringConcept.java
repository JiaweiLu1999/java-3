package com.example.java3.week4.spring;

import com.example.java3.week4.orm.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *  POJO
 *      Entity : hibernate
 *      DTO    : Restful api
 *      VO     :
 *  Bean
 *
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Why Spring ?
 *      1. IOC
 *      2. AOP
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  IOC -> concept
 *  Dependency Injection
 *      1. @Component, @Service, @Repository, @Controller, @Bean
 *      2. @Autowired
 *          Field Injection / Constructor Injection / Setter Injection
 *          By Type
 *          By Name : @Qualifier / reference name
 *      3. bean scopes
 *          Singleton(default)
 *          Prototype
 *          Session
 *          Request
 *          Global Session
 *      4. IOC container(Application Context)
 *
 *
 *  AOP
 *      1. Advice: @Before / @After / @Around / @AfterReturn / @AfterThrow
 *      2. AspectJ
 *      3. @PointCut
 *      log data / centralize duplicate code
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  MVC structure
 *      1. View : user interface
 *      2. Controller : Controller Layer + Service Layer
 *      3. Model : Repository Layer + Database Layer
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Spring Boot
 *      1. auto configuration
 *      2. application.properties for configuration (not xml)
 *      3. main method starter
 *      4. embedded tomcat + package application to jar
 *      5. actuator
 */

@SpringBootApplication
class SpringConcept {
    private static StudentService ss;

    @Autowired
    public SpringConcept(StudentService studentServiceImpl1) {
        ss = studentServiceImpl1;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringConcept.class, args);
        System.out.println(ss);
    }
}
@Configuration
class MyConfigure {
    @Bean
    public String myBean() {
        return "aaaa";
    }
}


@Component
interface StudentService {}
@Component
class StudentServiceImpl1 implements StudentService {

    private final StudentRepository r1;

    @Autowired
    private String myBean;

    @Autowired
    public StudentServiceImpl1(StudentRepository myBeanName) {
        this.r1 = myBeanName;
    }

//    @Autowired
//    @Qualifier("myBeanName")
//    private StudentRepository r2;

    @Override
    public String toString() {
        return "StudentServiceImpl1{} : " + myBean;
    }
}

//@Repository
//interface StudentRepository extends JpaRepository<Student, String> {}

@Component
interface StudentRepository {}

@Component(value = "myBeanName")
@Scope("prototype")
class StudentRepositoryImpl1 implements StudentRepository {
    @Override
    public String toString() {
        return "StudentRepositoryImpl1{}";
    }
}

