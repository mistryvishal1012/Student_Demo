package com.example.student.Service;

import com.example.student.Exception.NotFoundException;
import com.example.student.Model.Course;
import com.example.student.Model.Student;
import com.example.student.Repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> listAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public String deleteStudnet(long id) {
        System.out.println(id);
        Student optionalStudent = studentRepository.findById(id);
        if(optionalStudent instanceof Student){
             studentRepository.deleteById(id);
             return "Student With ID :" + id +" Deleted Successfully";
        }else{
            System.out.println("No Found Student With ID : "+id);
            throw new NotFoundException("Student with Id "+id+" Not Found");
        }
    }

    @Override
    public Student updateStudent(long id,Student student) {
        System.out.println(id);
        Student optionalStudent = studentRepository.findById(id);
        if(optionalStudent instanceof Student){
            Student studentToUpdate = optionalStudent;
            BeanUtils.copyProperties(student,studentToUpdate,"student_id");
            System.out.println(studentToUpdate);
            return studentRepository.saveAndFlush(studentToUpdate);
        }else{
            System.out.println("No Found Student With ID : "+id);
            throw new NotFoundException("Student with Id "+id+" Not Found");
        }
    }

    @Override
    public Student getStudent(long id) {
        Student optionalStudent = studentRepository.findById(id);
        if(optionalStudent instanceof Student){
            return optionalStudent;
        }else{
            System.out.println("No Found Student With ID : "+id);
            throw new NotFoundException("Student with Id "+id+" Not Found");
        }
    }

    @Override
    public Student addCourse(long id, Course course) {
        Student optionalStudent = studentRepository.findById(id);
        if(optionalStudent instanceof Student){
            Student student = optionalStudent;
            student.getStudent_enrolled_courses().add(course);
            return studentRepository.saveAndFlush(student);
        }else{
            System.out.println("No Found Student With ID : "+id);
            throw new NotFoundException("Student with Id "+id+" Not Found");
        }

    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.saveAndFlush(student);
    }

}
