package com.learningapi.LearningApi.models;

public class EnrollmentsDTO {
    private String courseName;
    private String instructedBy;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructedBy() {
        return instructedBy;
    }

    public void setInstructedBy(String instructedBy) {
        this.instructedBy = instructedBy;
    }
}
