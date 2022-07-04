package com.example.student.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class Course {

    private Long course_id;
    private String course_name;

    private List<Student> student_enrolled_courses;

    public List<Student> getStudent_enrolled_courses() {
        return student_enrolled_courses;
    }

    public Course(String course_name, List<Student> student_enrolled_courses) {
        this.course_name = course_name;
        this.student_enrolled_courses = student_enrolled_courses;
    }

    public void setStudent_enrolled_courses(List<Student> student_enrolled_courses) {
        this.student_enrolled_courses = student_enrolled_courses;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "course_id=" + course_id +
                ", course_name='" + course_name + '\'' +
                ", student_enrolled_courses=" + student_enrolled_courses +
                '}';
    }

    public Course(String course_name) {
        this.course_name = course_name;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}
