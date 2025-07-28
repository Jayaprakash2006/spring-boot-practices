package com.example.artgallery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="artist")
public class Artist {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int artistId;

    @Column(name="name")
    private String artistName;

    @Column(name="genre")
    private String genre;

    @ManyToMany
    @JoinTable(
        name="artist_gallery",
        joinColumns = @JoinColumn(name="artistid"),
        inverseJoinColumns = @JoinColumn(name="galleryid")
        )
    @JsonIgnoreProperties("artists")
    private List<Gallery> galleries;

    @OneToMany(mappedBy = "artist")
    @JsonIgnoreProperties("artist")
    private List<Art> arts;

    public Artist() {}

    public Artist(int artistId, String artistName, String genre, List<Gallery> galleries) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.genre = genre;
        this.galleries = galleries;
    }

    public int getArtistId() {
        return this.artistId;
    }
    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return this.artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return this.genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Gallery> getGalleries() {
        return this.galleries;
    }
    public void setGalleries(List<Gallery> galleries) {
        this.galleries = galleries;
    }
    public List<Art> getArts() {
        return this.arts;
    }

    public void setArts(List<Art> arts) {
        this.arts = arts;
    }

}