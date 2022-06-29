package com.example.student.Controller;


import com.example.student.Exception.NotFoundException;
import com.example.student.Model.Course;
import com.example.student.Service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping
    public ResponseEntity<List<Course>> list(){
        return new ResponseEntity<List<Course>>(courseService.listAllCourses(), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable long id){
        try {
            Course course = courseService.getCourse(id);
            return new ResponseEntity<Course>(course,HttpStatus.OK);
        }catch (NotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }



}
