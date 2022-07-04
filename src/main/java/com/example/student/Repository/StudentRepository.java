package com.example.student.Repository;

import com.example.student.DAO.StudentDAO;
import com.example.student.Model.Course;
import com.example.student.Model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository implements StudentDAO {

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student findById(long id) {
        return null;
    }

    @Override
    public Student saveAndFlush(Student student) {
        return null;
    }

    @Override
    public String deleteById(long id) {
        return null;
    }
}
