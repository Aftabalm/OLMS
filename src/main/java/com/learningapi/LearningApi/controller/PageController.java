package com.learningapi.LearningApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/signup")
    public String signup(){
        return "signup";
    }
    @RequestMapping("/home")
    public String home(){
        return "home";
    }
    @RequestMapping("/student")
    public String course(){
        return "student";
    }
    @RequestMapping("/course")
    public String courseDetails() { return "course";}
    @RequestMapping("/enrollment")
    public String getEnrollment() { return "enrollment";}
    @RequestMapping("/newEnrollment")
    public String getNewEnrollment() { return "enrollment";}
    @GetMapping("/profile")
    public String showProfilePage(){
        return "profile";
    }
    @GetMapping("/changeProfile")
    public String changeProfile() {
        return "changeProfile";
    }
    @GetMapping("/createCourse")
    public String createCourse() {
        return "createCourse";
    }
}


