package com.learningapi.LearningApi.repository;

import com.learningapi.LearningApi.entity.Courses;
import com.learningapi.LearningApi.entity.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Courses,Long> {
    Courses findCoursesById(long id);

}
