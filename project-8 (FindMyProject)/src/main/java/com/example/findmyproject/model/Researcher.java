package com.example.findmyproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="researcher")
public class Researcher {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int researcherId;

    @Column(name="name")
    private String researcherName;

    @Column(name="specialization")
    private String specialization;

    @ManyToMany
    @JoinTable(name="researcher_project",
        joinColumns = @JoinColumn(name="researcherid"),
        inverseJoinColumns = @JoinColumn(name="projectid"))
    @JsonIgnoreProperties("researchers")
    private List<Project> projects = new ArrayList<>();

    public Researcher() {}

    public Researcher(int researcherId, String researcherName, String specialization, List<Project> projects) {
        this.researcherId = researcherId;
        this.researcherName = researcherName;
        this.specialization = specialization;
        this.projects = projects;
    }

    public int getResearcherId() {
        return this.researcherId;
    }
    public void setResearcherId(int researcherId) {
        this.researcherId = researcherId;
    }

    public String getResearcherName() {
        return this.researcherName;
    }
    public void setResearcherName(String researcherName) {
        this.researcherName = researcherName;
    }

    public String getSpecialization() {
        return this.specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Project> getProjects() {
        return this.projects;
    }
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}