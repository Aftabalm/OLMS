package com.learningapi.LearningApi.repository;

import com.learningapi.LearningApi.entity.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentsRepository extends JpaRepository<Enrollments, Long> {

    List<Enrollments> findByUserId(long userId);
    Enrollments findEnrollmentsByUserIdAndCoursesId(long userId, long coursesId);
}
