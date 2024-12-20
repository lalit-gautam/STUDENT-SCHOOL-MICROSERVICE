package com.ultimatum.student.dto;

public class StudentResponse {

    private Long id;
    private String name;
    private int age;
    private String gender;
    private School school;

    public StudentResponse(){

    }

    public StudentResponse(Long id, String name, int age, String gender, School school) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.school = school;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
