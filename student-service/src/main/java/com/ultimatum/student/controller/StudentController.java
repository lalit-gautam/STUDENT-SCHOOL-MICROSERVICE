package com.ultimatum.student.controller;

import com.ultimatum.student.dto.StudentResponse;
import com.ultimatum.student.entity.Student;
import com.ultimatum.student.service.StudentService;
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

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> fetchAllStudents(){
        return new ResponseEntity<>(studentService.fetchStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> fetchStudentById(@PathVariable Long id){
        return new ResponseEntity<>(studentService.fetchStudentById(id), HttpStatus.OK);
    }
}
