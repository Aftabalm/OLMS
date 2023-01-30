package com.learningapi.LearningApi.controller;

import com.learningapi.LearningApi.entity.Courses;
import com.learningapi.LearningApi.entity.Enrollments;
import com.learningapi.LearningApi.entity.User;
import com.learningapi.LearningApi.models.*;
import com.learningapi.LearningApi.repository.CourseRepository;
import com.learningapi.LearningApi.repository.EnrollmentsRepository;
import com.learningapi.LearningApi.repository.UserRepository;
import com.learningapi.LearningApi.services.security.MyUserDetailsService;
import com.learningapi.LearningApi.services.UserService;
import com.learningapi.LearningApi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private MyUserDetailsService myuserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private EnrollmentsRepository enrollmentsRepository;
    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping("/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        if (userRepository.findUserByEmail(userDTO.getEmail()) != null) {
            return new ResponseEntity<>(new ApiResponse(false,"Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        userService.addUser(userDTO);
        return ResponseEntity.ok(new ApiResponse(true,"User registered successfully"));
    }
    @GetMapping("/profile/view")
    public ResponseEntity<?> viewProfile(){
        return userService.getProfile();
    }
    @PostMapping("/profile/update")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileChangeDTO updatedProfile){
        return userService.changeProfile(updatedProfile);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        User user = userRepository.findUserByEmail(authenticationRequest.getEmail());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/userDetails")
    public UserDTO showUser() throws Exception {
        return userService.getUserDetails();
    }

    @GetMapping("/user/courses/enrollment")
    public ResponseEntity<?> getEnrolledCourses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        User user = userRepository.findUserByEmail(currentUserEmail);

        List<Enrollments> enrollments = enrollmentsRepository.findByUserId(user.getId());

        enrollments.forEach(enrollment -> enrollment.getUser().setPassword(null));
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }

    @PostMapping("/newEnrollments/{courseId}")
    public ResponseEntity<?> enrollCourse(@PathVariable("courseId") long courseId) {
        Courses courses = courseRepository.findCoursesById(courseId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();
        User user = userRepository.findUserByEmail(currentUserId);

        if (enrollmentsRepository.findEnrollmentsByUserIdAndCoursesId(user.getId(), courseId) != null) {
            return new ResponseEntity<>(new ApiResponse(false, "enrollment already exist"), HttpStatus.BAD_REQUEST);
        }

        Enrollments enrollments = new Enrollments(user, courses, -1);
        enrollmentsRepository.save(enrollments);
        return ResponseEntity.ok(new ApiResponse(true, "enrollment successfull!"));
    }
}
