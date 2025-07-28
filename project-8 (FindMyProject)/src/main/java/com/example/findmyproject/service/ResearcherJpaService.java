package com.example.findmyproject.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.*;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.model.Project;
import com.example.findmyproject.repository.ResearcherJpaRepository;
import com.example.findmyproject.repository.ResearcherRepository;
import com.example.findmyproject.repository.ProjectJpaRepository;

@Service
public class ResearcherJpaService implements ResearcherRepository {

    @Autowired
    ResearcherJpaRepository db;

    @Autowired
    ProjectJpaRepository project_db;

    public ArrayList<Researcher> getResearchers() {
        return (ArrayList<Researcher>) db.findAll();
    }

    public Researcher addResearcher(Researcher researcher) {
        try {
            List<Project> projects = researcher.getProjects();
            List<Integer> projectsId = new ArrayList<>();
            for(Project project: projects) {
                projectsId.add(project.getProjectId());
            }
            List<Project> new_project = project_db.findAllById(projectsId);
            researcher.setProjects(new_project);
            return db.save(researcher);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Researcher getResearcher(int researcherId) {
        try {
            return db.findById(researcherId).get();
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Researcher updateResearcher(int researcherId, Researcher researcher) {
        try {
            Researcher ext = db.findById(researcherId).get();
            if(researcher.getResearcherName() != null) ext.setResearcherName(researcher.getResearcherName());
            if(researcher.getSpecialization() != null) ext.setSpecialization(researcher.getSpecialization());
            if(researcher.getProjects() != null) {
                List<Project> projects = researcher.getProjects();
                List<Integer> projectsId = new ArrayList<>();
                for(Project project: projects) {
                    projectsId.add(project.getProjectId());
                }
                List<Project> new_projects = project_db.findAllById(projectsId);
                ext.setProjects(new_projects);
            }
            return db.save(ext);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteResearcher(int researcherId) {
        try {
            Researcher researcher = db.findById(researcherId).get();
            List<Project> projects = researcher.getProjects();
            for(Project project: projects) {
                project.getResearchers().remove(researcher);
            }
            project_db.saveAll(projects);
            db.deleteById(researcherId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);

        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Project> getResearcherProject(int researcherId) {
        try {
            Researcher researcher = db.findById(researcherId).get();
            return researcher.getProjects();
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}