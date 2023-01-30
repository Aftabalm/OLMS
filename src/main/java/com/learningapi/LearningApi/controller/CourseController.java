package com.learningapi.LearningApi.controller;

import com.learningapi.LearningApi.entity.Courses;
import com.learningapi.LearningApi.entity.User;
import com.learningapi.LearningApi.models.ApiResponse;
import com.learningapi.LearningApi.models.CourseDTO;
import com.learningapi.LearningApi.repository.CourseRepository;
import com.learningapi.LearningApi.repository.EnrollmentsRepository;
import com.learningapi.LearningApi.services.CourseService;
import com.learningapi.LearningApi.services.EnrollmentsService;
import com.learningapi.LearningApi.services.UserService;
import com.learningapi.LearningApi.services.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class CourseController {
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    EnrollmentsRepository enrollmentsRepository;
    @Autowired
    EnrollmentsService enrollmentsService;
    @Autowired
    private CourseService courseService;
    @Autowired
    UserService userService;

    @GetMapping("/courses")
    public List<Courses> getAllCourses() {
        List<Courses> x = courseService.getAllCourses();
        User user = userService.getLoggedInUser();
        List<Courses> m = new ArrayList<>();
        for(Courses y : x){
            System.out.println(y);
            if(!y.getInstructor().equals(user.getFirstName())){
                m.add(y);
            }
        }
        return m;
    }

    @PostMapping("/courseDetails")
    public CourseDTO showCourse() throws Exception {
        return courseService.getCourseDetails();
    }

    @PostMapping("/addCourse")
    public void addCourse(@RequestBody CourseDTO courseDTO) {
        User user = userService.getLoggedInUser();
        Courses c = new Courses();
        c.setCourseName(courseDTO.getCourseName());
        c.setDescription(courseDTO.getDescription());
        c.setInstructor(user.getFirstName());
        c.setUser(user);
        courseRepository.save(c);
    }

    //
//    @PostMapping("/addEnrollments")
//    public void addEnrollments(@RequestBody Enrollments enrollments) {
//        enrollmentsService.addEnrollments(enrollments);
//    }
    @PostMapping("/deleteEnrollments/{enrollmentId}")
    public void removeEnrollment(@PathVariable long enrollmentId) {
        enrollmentsService.removeEnrollments(enrollmentId);
    }
//    @RequestMapping("/enrollments")
//    public List<Enrollments> getAllEnrollments() {
//        return  enrollmentsService.getAllEnrollments();
//    }
//    @PostMapping("/newEnrollment/{courseId}")
//    public ApiResponse newEnrollment(@PathVariable("courseId") long courseId) {
//        Courses courses = courseRepository.findCoursesById();
//        return new ApiResponse(true, "enrolled succeessfully");
//    }
}
