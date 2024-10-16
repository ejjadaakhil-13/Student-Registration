package com.example.model;

import java.util.Date;

public class Student {
    private int id;
    private String fullName;
    private String email;
    private String photoPath;
    private String className;
    private String gender;
    private int age;
    private Date dob;

    public Student() {}

    public Student(String fullName, String email, String photoPath, String className, String gender, int age, Date dob) {
        this.fullName = fullName;
        this.email = email;
        this.photoPath = photoPath;
        this.className = className;
        this.gender = gender;
        this.age = age;
        this.dob = dob;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhotoPath() { return photoPath; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }
}