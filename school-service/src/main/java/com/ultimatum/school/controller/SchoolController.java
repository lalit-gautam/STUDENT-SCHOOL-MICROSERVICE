package com.ultimatum.school.controller;

import com.ultimatum.school.entity.School;
import com.ultimatum.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public ResponseEntity<School> addSchool(@RequestBody School school){
        return new ResponseEntity<>(schoolService.addSchool(school), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<School>> fetchAllSchools(){
        return new ResponseEntity<>(schoolService.fetchAllSchools(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> fetchSchoolById(@PathVariable Long id){
        return new ResponseEntity<>(schoolService.fetchSchoolById(id), HttpStatus.OK);
    }

}
