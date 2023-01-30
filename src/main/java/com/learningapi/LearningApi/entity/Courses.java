package com.learningapi.LearningApi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "course_name")
    private String courseName;
    private String description;
    private String instructor;

    @ManyToOne
    @JoinColumn(name = "my_user_id")
    private User user;

    @OneToMany(mappedBy = "courses", cascade = CascadeType.REMOVE)
    private List<Enrollments> enrollments;

    public Courses() {
    }

    public Courses(String courseName, String description, String instructor, User user) {
        this.courseName = courseName;
        this.description = description;
        this.instructor = instructor;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
