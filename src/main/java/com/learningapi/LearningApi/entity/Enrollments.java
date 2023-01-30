package com.learningapi.LearningApi.entity;

import javax.persistence.*;

@Entity
public class Enrollments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "my_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses courses;
     public Enrollments(User user, Courses courses, int i) {
         this.user = user;
         this.courses = courses;
     }

    public Enrollments() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }
}
