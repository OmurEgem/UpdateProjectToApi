package com.example.updateprojecttoapi.repositories;


import com.example.updateprojecttoapi.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}