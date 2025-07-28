package com.example.artgallery.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;
import com.example.artgallery.model.Art;
import com.example.artgallery.model.Artist;
import com.example.artgallery.model.Gallery;
import com.example.artgallery.repository.ArtistRepository;
import com.example.artgallery.repository.ArtistJpaRepository;
import com.example.artgallery.repository.GalleryJpaRepository;
import com.example.artgallery.repository.ArtJpaRepository;

@Service
public class ArtistJpaService implements ArtistRepository {

    @Autowired
    ArtistJpaRepository db;

    @Autowired
    GalleryJpaRepository gallery_db;

    @Autowired
    ArtJpaRepository art_db;

    public ArrayList<Artist> getArtists() {
        return (ArrayList<Artist>)db.findAll();
    }

    public Artist addArtist(Artist artist) {
        try {
            List<Gallery> gallerys = artist.getGalleries();
            List<Integer> galleryIds = new ArrayList<>();
            for(Gallery gallery: gallerys) {
                galleryIds.add(gallery.getGalleryId());
            }
            List<Gallery> new_gallery = gallery_db.findAllById(galleryIds);
            if(galleryIds.size() != new_gallery.size()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            artist.setGalleries(new_gallery);
            return db.save(artist);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some of the Id's are missing.");
        }
    }

    public Artist getArtist(int artistId) {
        try {
            return db.findById(artistId).get();
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Artist updateArtist(int artistId, Artist artist) {
        try {
            Artist ext = db.findById(artistId).get();
            if(artist.getArtistName() != null) ext.setArtistName(artist.getArtistName());
            if(artist.getGenre() != null) ext.setGenre(artist.getGenre());
            if(artist.getGalleries() != null) {
                List<Gallery> galleries = artist.getGalleries();
                List<Integer> galleryIds = new ArrayList<>();
                for(Gallery gallery: galleries) {
                    galleryIds.add(gallery.getGalleryId());
                }
                
                List<Gallery> new_gallery = gallery_db.findAllById(galleryIds);

                if(galleryIds.size() != new_gallery.size()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some Id's are missing.");
                ext.setGalleries(new_gallery);
            }
            return db.save(ext);
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //ArtistJpaService.java
    public void deleteArtist(int artistId) {
        if (!db.existsById(artistId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        db.deleteById(artistId);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public List<Art> getArtistArts(int artistId) {
        try {
            Artist artist = db.findById(artistId).get();
            return art_db.findByArtist(artist);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Gallery> getArtistGalleries(int artistId) {
        try {
            Artist artist = db.findById(artistId).get();
            return artist.getGalleries();
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}