package com.example.student.DAO;

import com.example.student.Model.Student;

import java.util.List;

public interface StudentDAO {


    List<Student> findAll();

    Student findById(long id);

    Student saveAndFlush(Student student);

    String deleteById(long id);
}
