package com.example.updateprojecttoapi.repositories;


import com.example.updateprojecttoapi.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}