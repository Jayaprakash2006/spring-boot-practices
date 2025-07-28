package com.example.artgallery.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import com.example.artgallery.model.Gallery;
import com.example.artgallery.model.Artist;
import com.example.artgallery.repository.GalleryRepository;
import com.example.artgallery.repository.GalleryJpaRepository;
import com.example.artgallery.repository.ArtistJpaRepository;

@Service
public class GalleryJpaService implements GalleryRepository {

    @Autowired
    private GalleryJpaRepository db;
    
    @Autowired
    private ArtistJpaRepository artist_db;

    public ArrayList<Gallery> getGalleries() {
        return (ArrayList<Gallery>) db.findAll();
    }

    public Gallery addGallery(Gallery gallery) {
        try {
            List<Artist> artists = gallery.getArtists();
            List<Integer> artistIds = new ArrayList<>();
            for(Artist artist: artists) {
                artistIds.add(artist.getArtistId());
            }
            List<Artist> new_artists = artist_db.findAllById(artistIds);
            gallery.setArtists(new_artists);
            for(Artist artist: new_artists) {
                artist.getGalleries().add(gallery);
            }
            Gallery saved = db.save(gallery);
            artist_db.saveAll(new_artists);
            return saved;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Gallery getGallery(int galleryId) {
        try {
            return db.findById(galleryId).get();
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Gallery updateGallery(int galleryId, Gallery gallery) {
        try {
            Gallery ext = db.findById(galleryId).get();
            if(gallery.getGalleryName() != null) {
                ext.setGalleryName(gallery.getGalleryName());
            }
            if(gallery.getLocation() != null) {
                ext.setLocation(gallery.getLocation());
            }
            if(gallery.getArtists() != null) {
                List<Artist> artists = gallery.getArtists();
                List<Integer> artistsId = new ArrayList<>();
                for(Artist artist: artists) {
                    artistsId.add(artist.getArtistId());
                }
                for(Artist artist: ext.getArtists()) {
                    artist.getGalleries().remove(ext);
                }

                List<Artist> new_artists = artist_db.findAllById(artistsId);
                
                for(Artist artist: new_artists) {
                    artist.getGalleries().add(ext);
                }

                ext.setArtists(new_artists);
                artist_db.saveAll(new_artists);

            }
            return db.save(ext);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteGallery(int galleryId) {
        try {
            if (!db.existsById(galleryId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
            Gallery gallery = db.findById(galleryId).get();
            List<Artist> artists = gallery.getArtists();
            for(Artist artist: artists) {
                artist.getGalleries().remove(gallery);
            }
            artist_db.saveAll(artists);
            db.deleteById(galleryId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public List<Artist> getGalleryArtists(int galleryId) {
        try {
            Gallery gallery = db.findById(galleryId).get();
            return artist_db.findByGalleries(gallery);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}