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
import com.example.artgallery.model.Gallery;
import com.example.artgallery.model.Artist;
import com.example.artgallery.service.GalleryJpaService;

@RestController
public class GalleryController {

    @Autowired
    private GalleryJpaService service;

    @GetMapping("/galleries")
    public ArrayList<Gallery> getGalleries() {
        return service.getGalleries();
    }

    @PostMapping("/galleries")
    public Gallery addGallery(@RequestBody Gallery gallery) {
        return service.addGallery(gallery);
    }

    @GetMapping("/galleries/{galleryId}")
    public Gallery getGallery(@PathVariable("galleryId") int galleryId) {
        return service.getGallery(galleryId);
    }

    @PutMapping("/galleries/{galleryId}")
    public Gallery updateGallery(@PathVariable("galleryId") int galleryId, @RequestBody Gallery gallery) {
        return service.updateGallery(galleryId, gallery);
    }

    @DeleteMapping("/galleries/{galleryId}")
    public void deleteGallery(@PathVariable("galleryId") int galleryId) {
        service.deleteGallery(galleryId);
    }

    @GetMapping("/galleries/{galleryId}/artists")
    public List<Artist> getGalleryArtists(@PathVariable("galleryId") int galleryId) {
        return service.getGalleryArtists(galleryId);
    }
}