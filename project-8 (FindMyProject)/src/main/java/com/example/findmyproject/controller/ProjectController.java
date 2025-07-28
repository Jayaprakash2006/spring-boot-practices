package com.example.findmyproject.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.service.ProjectJpaService;

@RestController
public class ProjectController {

    @Autowired
    ProjectJpaService service;

    @GetMapping("/researchers/projects")
    public ArrayList<Project> getProjects() {
        return service.getProjects();
    }

    @PostMapping("/researchers/projects")
    public Project addProject(@RequestBody Project project) {
        return service.addProject(project);
    }

    @GetMapping("/researchers/projects/{projectId}")
    public Project getProject(@PathVariable("projectId") int projectId) {
        return service.getProject(projectId);
    }

    @PutMapping("/researchers/projects/{projectId}")
    public Project updateProject(@PathVariable("projectId") int projectId, @RequestBody Project project) {
        return service.updateProject(projectId, project);
    }

    @DeleteMapping("/researchers/projects/{projectId}")
    public void deleteProject(@PathVariable("projectId") int projectId) {
        service.deleteProject(projectId);
    }

    @GetMapping("/projects/{projectId}/researchers")
    public List<Researcher> getProjectResearchers(@PathVariable("projectId") int projectId) {
        return service.getProjectResearchers(projectId);
    }
}