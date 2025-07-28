package com.example.findmyproject.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;
import com.example.findmyproject.model.Project;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.repository.ProjectJpaRepository;
import com.example.findmyproject.repository.ResearcherJpaRepository;
import com.example.findmyproject.repository.ProjectRepository;

@Service
public class ProjectJpaService implements ProjectRepository {

    @Autowired
    private ProjectJpaRepository db;

    @Autowired
    private ResearcherJpaRepository researcher_db;

    public ArrayList<Project> getProjects() {
        return (ArrayList<Project>) db.findAll();
    }

    public Project addProject(Project project) {
        try {
            List<Researcher> researchers = project.getResearchers();
            List<Integer> researchersId = new ArrayList<>();
            for(Researcher researcher: researchers) {
                researchersId.add(researcher.getResearcherId());
            }
            List<Researcher> new_researcher = researcher_db.findAllById(researchersId);
            if(researchersId.size() != new_researcher.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some id's are missing");
            }

            project.setResearchers(new_researcher);
            Project saved = db.save(project);

            for(Researcher researcher: new_researcher) {
                researcher.getProjects().add(saved);
            }

            researcher_db.saveAll(new_researcher);

            return saved;
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Project getProject(int projectId) {
        try {
            return db.findById(projectId).get();
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Project updateProject(int projectId, Project project) {
        try {
            Project ext = db.findById(projectId).get();
            if(project.getProjectName() != null) ext.setProjectName(project.getProjectName());
            if(project.getBudget() != 0.0) ext.setBudget(project.getBudget());
            if(project.getResearchers() != null) {
                List<Researcher> researchers = project.getResearchers();
                List<Integer> researchersId = new ArrayList<>();
                for(Researcher researcher: researchers) {
                    researchersId.add(researcher.getResearcherId());
                }
                List<Researcher> new_researcher = researcher_db.findAllById(researchersId);
                if(researchersId.size() != new_researcher.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some id's are missing");
                }
                for(Researcher researcher: ext.getResearchers()) {
                    researcher.getProjects().remove(ext);
                }
                for(Researcher researcher: new_researcher) {
                    researcher.getProjects().add(ext);
                }
                ext.setResearchers(new_researcher);
            }
            Project saved = db.save(ext);
            return saved;
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Some id's are missing");
        }
    }

    public void deleteProject(int projectId) {
        try {
            Project project = db.findById(projectId).get();
            List<Researcher> researchers = project.getResearchers();
            for(Researcher researcher: researchers) {
                researcher.getProjects().remove(project);
            }
            researcher_db.saveAll(researchers);
            db.deleteById(projectId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Researcher> getProjectResearchers(int projectId) {
        try {
            Project project = db.findById(projectId).get();
            List<Researcher> researchers = researcher_db.findByProjects(project);
            return researchers;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}