package com.example.student.Service;

import com.example.student.Exception.NotFoundException;
import com.example.student.Model.Course;
import com.example.student.Model.Student;
import com.example.student.Repository.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{


    @Autowired
    private CourseRepository courseRepository;


    @Override
    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourse(long id) {
        Course optionalCourse = courseRepository.findById(id);
        if(optionalCourse instanceof Course){
            return optionalCourse;
        }else{
            System.out.println("No Found Course With ID : "+id);
            throw new NotFoundException("Course with Id "+id+" Not Found");
        }
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.saveAndFlush(course);
    }

    @Override
    public Course updateCourse(long id, Course course) {
        Course optionalCourse = courseRepository.findById(id);
        if(optionalCourse instanceof Course){
            Course courseToUpdate = optionalCourse;
            BeanUtils.copyProperties(course,courseToUpdate,"course_id");
            return courseRepository.saveAndFlush(courseToUpdate);
        }else{
            System.out.println("No Found Course With ID : "+id);
            throw new NotFoundException("Course with Id "+id+" Not Found");
        }
    }

    @Override
    public String deleteCourse(long id) {
        Course optionalCourse = courseRepository.findById(id);
        if(optionalCourse instanceof Course){
            courseRepository.deleteById(id);
            return "Cousre With ID "+id+" Deleted Succesfully";
        }else{
            System.out.println("No Found Course With ID : "+id);
            throw new NotFoundException("Course with Id "+id+" Not Found");
        }
    }
}
