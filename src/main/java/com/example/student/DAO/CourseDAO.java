package com.example.student.DAO;

import com.example.student.Model.Course;

import java.util.List;

public interface CourseDAO {

    List<Course> findAll();

    Course findById(long id);

    Course saveAndFlush(Course course);

    String deleteById(long id);
}
