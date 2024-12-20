package com.ultimatum.school.service;

import com.ultimatum.school.entity.School;
import com.ultimatum.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    public School addSchool(School school){
        return schoolRepository.save(school);
    }

    public List<School> fetchAllSchools(){
        return schoolRepository.findAll();
    }

    public School fetchSchoolById(Long id){
        return schoolRepository.findById(id).orElse(null);
    }
}
