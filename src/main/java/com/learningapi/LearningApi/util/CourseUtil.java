package com.learningapi.LearningApi.util;

import com.learningapi.LearningApi.models.CourseDTO;
import com.learningapi.LearningApi.entity.Courses;
import org.springframework.stereotype.Component;

@Component
public class CourseUtil {
    public CourseDTO entityToDto(Courses courses) {
        CourseDTO coursedto = new CourseDTO();
        coursedto.setCourseName(courses.getCourseName());
        coursedto.setDescription(courses.getDescription());
        return coursedto;
    }
    public Courses dtoToEntity(CourseDTO courseDTO) {
        Courses courses = new Courses();
        courses.setCourseName(courseDTO.getCourseName());
        courses.setDescription(courseDTO.getDescription());
        return courses;
    }
}
