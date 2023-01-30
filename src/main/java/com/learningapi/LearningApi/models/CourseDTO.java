package com.learningapi.LearningApi.models;

public class CourseDTO {
    private String courseName;
    private String description;

    public CourseDTO() {
    }

    public CourseDTO(String courseName, String description) {
        this.courseName = courseName;
        this.description = description;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
