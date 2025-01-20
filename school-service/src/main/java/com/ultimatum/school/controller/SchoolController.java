package com.ultimatum.school.controller;

import com.ultimatum.school.entity.School;
import com.ultimatum.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/school")
@RestController
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private Environment environment;

    @PostMapping
    public ResponseEntity<School> addSchool(@RequestBody School school){
        return new ResponseEntity<>(schoolService.addSchool(school), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<School>> fetchAllSchools(){
        System.out.println("School Service Port : " + environment.getProperty("server.port"));
        return new ResponseEntity<>(schoolService.fetchAllSchools(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> fetchSchoolById(@PathVariable Long id){
        System.out.println("Port Running on Service : " + environment.getProperty("server.port"));
        return new ResponseEntity<>(schoolService.fetchSchoolById(id), HttpStatus.OK);
    }

}
