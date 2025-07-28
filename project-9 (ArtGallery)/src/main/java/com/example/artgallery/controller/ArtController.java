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
import com.example.artgallery.service.ArtJpaService;

@RestController
public class ArtController {

    @Autowired
    private ArtJpaService service;

    @GetMapping("/galleries/artists/arts")
    public ArrayList<Art> getArts() {
        return service.getArts();
    }

    @PostMapping("/galleries/artists/arts")
    public Art addArt(@RequestBody Art art) {
        return service.addArt(art);
    }

    @GetMapping("/galleries/artists/arts/{artId}")
    public Art getArt(@PathVariable("artId") int artId) {
        return service.getArt(artId);
    }
    
    @PutMapping("/galleries/artists/arts/{artId}")
    public Art updateArt(@PathVariable("artId") int artId, @RequestBody Art art) {
        return service.updateArt(artId, art);
    }

    @DeleteMapping("/galleries/artists/arts/{artId}")
    public void deleteArt(@PathVariable("artId") int artId) {
        service.deleteArt(artId);
    }

    @GetMapping("/arts/{artId}/artist")
    public Artist getArtistById(@PathVariable("artId") int artId) {
        return service.getArtistById(artId);
    }

}