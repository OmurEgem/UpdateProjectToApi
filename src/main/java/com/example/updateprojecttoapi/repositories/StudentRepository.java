package com.example.updateprojecttoapi.repositories;


import com.example.updateprojecttoapi.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}