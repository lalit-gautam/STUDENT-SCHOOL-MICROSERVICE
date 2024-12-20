package com.ultimatum.student.service;

import com.ultimatum.student.dto.School;
import com.ultimatum.student.dto.StudentResponse;
import com.ultimatum.student.entity.Student;
import com.ultimatum.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public StudentResponse fetchStudentById(Long id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            School school = restTemplate.getForObject("http://SCHOOL-SERVICE/school/" + student.get().getSchoolId(), School.class);
            return new StudentResponse(
                    student.get().getId(),
                    student.get().getName(),
                    student.get().getAge(),
                    student.get().getGender(),
                    school);
        }
        return null;
    }

    public List<Student> fetchStudents(){
        return studentRepository.findAll();
    }


}
