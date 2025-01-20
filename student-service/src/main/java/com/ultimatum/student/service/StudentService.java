package com.ultimatum.student.service;

import com.ultimatum.student.dto.School;
import com.ultimatum.student.dto.StudentResponse;
import com.ultimatum.student.entity.Student;
import com.ultimatum.student.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public StudentResponse fetchStudentById(Long id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()) {
            return null;
        }
            School school = restTemplate.getForObject("http://SCHOOL-SERVICE/school/" + student.get().getSchoolId(), School.class);
            if(school == null){
                return null;
            }
            return new StudentResponse(
                    student.get().getId(),
                    student.get().getName(),
                    student.get().getAge(),
                    student.get().getGender(),
                    school);

    }

    public List<Student> fetchStudents(){
        return studentRepository.findAll();
    }

    public ResponseEntity<StudentResponse> fallback(Long id, Throwable t) {
        // Providing a fallback response
        StudentResponse fallbackResponse = new StudentResponse();
        fallbackResponse.setId(id);
        fallbackResponse.setName("Unknown");
        fallbackResponse.setAge(0);
        fallbackResponse.setGender("N/A");
        fallbackResponse.setSchool(null); // No school details available
        logger.error("Fallback triggered for id: {}, error: {}", id, t.getMessage());
        return new ResponseEntity<>(fallbackResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }


}
