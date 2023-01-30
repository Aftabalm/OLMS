package com.learningapi.LearningApi.services;

import com.learningapi.LearningApi.entity.Courses;
import com.learningapi.LearningApi.entity.User;
import com.learningapi.LearningApi.models.ApiResponse;
import com.learningapi.LearningApi.models.EnrollmentsDTO;
import com.learningapi.LearningApi.entity.Enrollments;
import com.learningapi.LearningApi.repository.CourseRepository;
import com.learningapi.LearningApi.repository.EnrollmentsRepository;
import com.learningapi.LearningApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentsService {
    @Autowired
    private EnrollmentsRepository enrollmentsRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Enrollments> getAllEnrollments() {
        return enrollmentsRepository.findAll();
    }
    public void addEnrollments(Enrollments enrollments) {
        enrollmentsRepository.save(enrollments);
    }
    public void removeEnrollments(long enrollmentId) {
        Optional<Enrollments> enrollments = enrollmentsRepository.findById(enrollmentId);
        enrollmentsRepository.deleteById(enrollmentId);
    }

//    public ApiResponse newEnrollment(EnrollmentsDTO enrollmentsDTO) {
//
//
//        Enrollments enrollments = new Enrollments();
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserEmail = authentication.getName();
//        User user = userRepository.findUserByUserId(currentUserEmail);
//
//        Courses courses = courseRepository.findCoursesById(courseId);
//
//        Enrollments prevEnrollment = enrollmentsRepository.findEnrollmentsByUserIdAndCoursesId(user.getId(), courseId);
//        if(prevEnrollment != null){
//            return new ApiResponse(false, "already enrolled in the course");
//        }
//
//        enrollments.setCourses(courses);
//        enrollments.setUser(user);
//        enrollmentsRepository.save(enrollments);
//        return new ApiResponse(true, "enrolled succeessfully");
//    }
}
