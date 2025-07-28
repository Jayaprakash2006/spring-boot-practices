package com.example.artgallery.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import com.example.artgallery.model.Art;
import com.example.artgallery.model.Artist;
import com.example.artgallery.model.Gallery;
import com.example.artgallery.service.ArtistJpaService;

@RestController
public class ArtistController {

    @Autowired
    private ArtistJpaService service;

    @GetMapping("/galleries/artists")
    public ArrayList<Artist> getArtists() {
        return service.getArtists();
    }

    @PostMapping("/galleries/artists")
    public Artist addArtist(@RequestBody Artist artist) {
        return service.addArtist(artist);
    }

    @GetMapping("/galleries/artists/{artistId}")
    public Artist getArtist(@PathVariable("artistId") int artistId) {
        return service.getArtist(artistId);
    }
    
    @PutMapping("/galleries/artists/{artistId}")
    public Artist updateArtist(@PathVariable("artistId") int artistId, @RequestBody Artist artist) {
        return service.updateArtist(artistId, artist);
    }

    @DeleteMapping("/galleries/artists/{artistId}")
    public void deleteArtist(@PathVariable("artistId") int artistId) {
        service.deleteArtist(artistId);
    }

    @GetMapping("/artists/{artistId}/arts")
    public List<Art> getArtistArts(@PathVariable("artistId") int artistId) {
        return service.getArtistArts(artistId);
    }

    @GetMapping("/artists/{artistId}/galleries")
    public List<Gallery> getArtistGalleries(@PathVariable("artistId") int artistId) {
        return service.getArtistGalleries(artistId);
    }

}