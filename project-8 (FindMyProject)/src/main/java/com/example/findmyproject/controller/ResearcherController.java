package com.example.findmyproject.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.ArrayList;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.model.Project;
import com.example.findmyproject.service.ResearcherJpaService;

@RestController
public class ResearcherController {

    @Autowired
    private ResearcherJpaService service;

    @GetMapping("/researchers")
    public ArrayList<Researcher> getResearchers() {
        return service.getResearchers();
    }

    @PostMapping("/researchers")
    public Researcher addResearcher(@RequestBody Researcher researcher) {
        return service.addResearcher(researcher);
    }

    @GetMapping("/researchers/{researcherId}")
    public Researcher getResearcher(@PathVariable("researcherId") int researcherId) {
        return service.getResearcher(researcherId);
    }

    @PutMapping("/researchers/{researcherId}")
    public Researcher updateResearcher(@PathVariable("researcherId") int researcherId, @RequestBody Researcher researcher) {
        return service.updateResearcher(researcherId, researcher);
    }

    @DeleteMapping("/researchers/{researcherId}")
    public void deleteResearcher(@PathVariable("researcherId") int researcherId) {
        service.deleteResearcher(researcherId);
    }

    @GetMapping("/researchers/{researcherId}/projects")
    public List<Project> getResearcherProject(@PathVariable("researcherId") int researcherId) {
        return service.getResearcherProject(researcherId);
    }
}