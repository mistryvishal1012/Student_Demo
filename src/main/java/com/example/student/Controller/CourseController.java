package com.example.student.Controller;


import com.example.student.Exception.NotFoundException;
import com.example.student.Model.Course;
import com.example.student.Model.Student;
import com.example.student.Service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;


    @GetMapping(path = "/testConnection")
    public ResponseEntity<Boolean> testConnection(){
        return new ResponseEntity<Boolean>(courseService.testConnection(),HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('course:read')")
    public ResponseEntity<List<Course>> list(){
        return new ResponseEntity<List<Course>>(courseService.listAllCourses(), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("{id}")
    @PreAuthorize("hasAnyAuthority('course:read')")
    public ResponseEntity<Course> getCourseById(@PathVariable long id){
        try {
            Course course = courseService.getCourse(id);
            return new ResponseEntity<Course>(course,HttpStatus.OK);
        }catch (NotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }


    @PostMapping
    @PreAuthorize("hasAnyAuthority('course:write')")
    public ResponseEntity<Course> create(@RequestBody final Course course){
        System.out.println(course);
        return new ResponseEntity<Course>(courseService.addCourse(course),HttpStatus.CREATED);
    }

}
