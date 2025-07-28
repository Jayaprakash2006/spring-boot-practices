package com.example.findmyproject.repository;

import java.util.List;
import java.util.ArrayList;
import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;

public interface ProjectRepository {

    ArrayList<Project> getProjects();
    Project addProject(Project project);
    Project getProject(int projectId);
    Project updateProject(int projectId, Project project);
    void deleteProject(int projectId);
    List<Researcher> getProjectResearchers(int projectId);

}