package com.example.sqlitedatabase;

public class StudentData {
    private String name;
    private String qualification;
    private String dob;

    public StudentData(String name, String qualification, String dob) {
        this.name = name;
        this.qualification = qualification;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public String getQualification() {
        return qualification;
    }

    public String getDob() {
        return dob;
    }
}
