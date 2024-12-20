package com.ultimatum.student.dto;

public class School {
    private Long id;
    private String schoolName;
    private String location;
    private String principalName;

    public School(){

    }

    public School(Long id, String location, String schoolName, String principalName) {
        this.id = id;
        this.location = location;
        this.schoolName = schoolName;
        this.principalName = principalName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }
}
