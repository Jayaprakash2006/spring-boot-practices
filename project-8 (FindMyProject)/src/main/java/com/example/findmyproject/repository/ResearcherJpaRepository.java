package com.example.findmyproject.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.model.Project;

@Repository
public interface ResearcherJpaRepository extends JpaRepository<Researcher, Integer> {
    List<Researcher> findByProjects(Project project);
}