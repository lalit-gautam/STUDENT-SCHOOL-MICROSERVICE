package com.ultimatum.student.controller;

import com.ultimatum.student.dto.StudentResponse;
import com.ultimatum.student.entity.Student;
import com.ultimatum.student.service.StudentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.http.conn.HttpHostConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> fetchAllStudents(){
        return new ResponseEntity<>(studentService.fetchStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "schoolService", fallbackMethod = "fallBackMessage")
    public ResponseEntity<StudentResponse> fetchStudentById(@PathVariable Long id){
        StudentResponse studentResponse =  studentService.fetchStudentById(id);
        if(studentResponse == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    public ResponseEntity<String> fallBackMessage(Long id, Throwable t){
        if(t instanceof HttpHostConnectException || t instanceof IllegalStateException){
            logger.error("School service is down. Fallback triggered for id: {}, error: {}", id, t.getMessage());
            return new ResponseEntity<>("School service is down." + t.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        logger.error("Unknown error for id: {}, error: {}", id, t.getMessage());
        return new ResponseEntity<>("Cannot find the Student Id." + id, HttpStatus.NOT_FOUND);

    }


}
