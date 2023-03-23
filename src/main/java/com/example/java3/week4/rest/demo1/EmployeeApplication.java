package com.example.java3.week4.rest.demo1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }
}

/**
 *
 *  controller package
 *      --EmployeeController class
 *      --DepartmentController
 *  service package
 *      --EmployeeService class
 *  repo package
 *      --EmployeeRepository class
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  employee package
 *      --Controller
 *      --Service
 *      --Repository
 *  depart package
 *      --Controller
 *      --Service
 *      --..
 *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *  A good restful API
 *      1. endpoint => /noun instead of /createStudent..
 *         http method
 *         status code
 *         response body / dto
 *      2. security
 *      3. performance
 *      4. documentation
 *      5. OOD design / SOLID
 *      6. exception + log
 *      7. test
 *          unit test
 *          integration test
 *          function test / automation test
 *          performance test
 *          behavior test
 *          security test
 *          smoke test / regression test
 */