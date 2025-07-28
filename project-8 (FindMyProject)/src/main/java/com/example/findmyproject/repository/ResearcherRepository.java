package com.example.findmyproject.repository;

import java.util.List;
import java.util.ArrayList;
import com.example.findmyproject.model.Researcher;
import com.example.findmyproject.model.Project;

public interface ResearcherRepository {

    ArrayList<Researcher> getResearchers();
    Researcher addResearcher(Researcher researcher);
    Researcher getResearcher(int researcherId);
    Researcher updateResearcher(int researcherId, Researcher researcher);
    void deleteResearcher(int researcherId);
    List<Project> getResearcherProject(int researcherId);

}