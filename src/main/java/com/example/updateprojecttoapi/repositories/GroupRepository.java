package com.example.updateprojecttoapi.repositories;


import com.example.updateprojecttoapi.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}