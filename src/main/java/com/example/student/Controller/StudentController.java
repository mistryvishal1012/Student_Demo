package com.example.student.Controller;


import com.example.student.Exception.NotFoundException;
import com.example.student.Model.Course;
import com.example.student.Model.Student;
import com.example.student.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;



    //hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')
    @GetMapping
    @PreAuthorize("hasAnyAuthority('student:read')")
    public ResponseEntity<List<Student>> list(){
        System.out.println("GET Students");
        List<Student> students = studentService.listAllStudents();
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("{id}")
    @PreAuthorize("hasAnyAuthority('student:read')")
    public ResponseEntity<Student> get(@PathVariable Long id){
        System.out.println("GET Students");
        try{
            Student student = studentService.getStudent(id);
            return  new ResponseEntity<Student>(student,HttpStatus.OK);
        }catch (NotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('student:write')")
    public ResponseEntity<Student> create(@RequestBody final Student student){
        System.out.println(student);
        return new ResponseEntity<Student>(studentService.addStudent(student),HttpStatus.CREATED);
    }

    @PostMapping(path = "/addCourse/{id}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public ResponseEntity<Student> addCourse(@PathVariable Long id,@RequestBody Course course){
        System.out.println("GET Students");
        try{
            Student student = studentService.getStudent(id);
            studentService.addCourse(id,course);
            return  new ResponseEntity<Student>(student,HttpStatus.OK);
        }catch (NotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    @PreAuthorize("hasAnyAuthority('student:write')")
    public ResponseEntity<Student> update(@PathVariable Long id,@RequestBody final Student student){
        System.out.println(id);
        try{
            return new ResponseEntity<Student>(studentService.updateStudent(id,student),HttpStatus.CREATED);
        }catch (NotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyAuthority('student:write')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        System.out.println(id);
        try{
            return new ResponseEntity<String>(studentService.deleteStudnet(id),HttpStatus.OK);
        }catch (NotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

}
