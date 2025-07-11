package com.example.wordlyweek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="writer")
public class Writer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int writerId;

    @Column(name="name")
    private String writerName;

    @Column(name="bio")
    private String bio;

    @ManyToMany
    @JoinTable(name="writer_magazine",
        joinColumns = @JoinColumn(name="writerid"),
        inverseJoinColumns = @JoinColumn(name="magazineid"))
    @JsonIgnoreProperties("writers")
    List<Magazine> magazines = new ArrayList<>();

    public Writer() {}

    public Writer(int writerId, String writerName, String bio, List<Magazine> magazines) {
        this.writerId = writerId;
        this.writerName = writerName;
        this.bio = bio;
        this.magazines = magazines;
    }

    public int getWriterId() {
        return this.writerId;
    }
    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public String getWriterName() {
        return this.writerName;
    }
    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getBio() {
        return this.bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Magazine> getMagazines() {
        return this.magazines;
    }
    public void setMagazines(List<Magazine> magazines) {
        this.magazines = magazines;
    }
}