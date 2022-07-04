package com.example.student.Model;

import java.io.Serializable;
import java.util.List;


public class Student implements Serializable {


    private Long student_id;
    private String student_name;

    private int student_graducation_year;

    private List<Course> student_enrolled_courses;

    public Student(String student_name, int student_graducation_year, List<Course> student_enrolled_courses) {
        this.student_name = student_name;
        this.student_graducation_year = student_graducation_year;
        this.student_enrolled_courses = student_enrolled_courses;
    }

    public int getStudent_graducation_year() {
        return student_graducation_year;
    }

    public void setStudent_graducation_year(int student_graducation_year) {
        this.student_graducation_year = student_graducation_year;
    }

    public List<Course> getStudent_enrolled_courses() {
        return student_enrolled_courses;
    }

    public void setStudent_enrolled_courses(List<Course> student_enrolled_courses) {
        this.student_enrolled_courses = student_enrolled_courses;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", student_name='" + student_name + '\'' +
                ", student_graducation_year=" + student_graducation_year +
                ", student_enrolled_courses=" + student_enrolled_courses +
                '}';
    }


    public Student() {
    }

}

