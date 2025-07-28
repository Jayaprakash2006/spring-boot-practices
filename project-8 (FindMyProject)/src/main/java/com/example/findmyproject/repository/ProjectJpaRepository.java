package com.example.findmyproject.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;

@Repository
public interface ProjectJpaRepository extends JpaRepository<Project, Integer> {
    
}