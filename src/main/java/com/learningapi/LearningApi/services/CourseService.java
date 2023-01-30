package com.learningapi.LearningApi.services;

import com.learningapi.LearningApi.models.CourseDTO;
import com.learningapi.LearningApi.entity.Courses;
import com.learningapi.LearningApi.repository.CourseRepository;
import com.learningapi.LearningApi.util.CourseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseUtil courseUtil;

    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addCourse(Courses courses) {
        courseRepository.save(courses);
    }

    public CourseDTO getCourseDetails() {
        CourseDTO courseDTO = new CourseDTO();
        Courses courses = new Courses();
        courseDTO.setCourseName(courses.getCourseName());
        courseDTO.setDescription(courses.getDescription());
        return courseDTO;
    }
}
