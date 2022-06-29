package com.example.student.Service;

import com.example.student.Model.Course;

import java.util.List;

public interface CourseService {

    List<Course> listAllCourses();

    Course getCourse(long id);

    Course addCourse(Course course);

    Course updateCourse(long id,Course course);

    String deleteCourse(long id);

}
