package com.example.artgallery.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;
import com.example.artgallery.model.Art;
import com.example.artgallery.model.Artist;
import com.example.artgallery.repository.ArtRepository;
import com.example.artgallery.repository.ArtJpaRepository;
import com.example.artgallery.repository.ArtistJpaRepository;

@Service
public class ArtJpaService implements ArtRepository {

    @Autowired
    private ArtJpaRepository db;
   
    @Autowired
    private ArtistJpaRepository artist_db;

    public ArrayList<Art> getArts() {
        return (ArrayList<Art>) db.findAll();
    }

    public Art addArt(Art art) {
        try {
            int artistId = art.getArtist().getArtistId();
            Artist new_artist = artist_db.findById(artistId).get();
            art.setArtist(new_artist);
            return db.save(art);
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Art getArt(int artId) {
        try {
            return db.findById(artId).get();
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Art updateArt(int artId, Art art) {
        try {
            Art ext = db.findById(artId).get();
            if(art.getArtTitle() != null) ext.setArtTitle(art.getArtTitle());
            if(art.getTheme() != null) ext.setTheme(art.getTheme());
            if(art.getArtist() != null) {
                int artistId = art.getArtist().getArtistId();
                Artist new_artist = artist_db.findById(artistId).get();
                ext.setArtist(new_artist);
            }
            return db.save(ext);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    public void deleteArt(int artId) {
        try {
            Art art = db.findById(artId).get();
            List<Artist> artists = artist_db.findByArts(art);
            for(Artist artist: artists) {
                artist.getArts().remove(art);
            }
            artist_db.saveAll(artists);
            
            db.deleteById(artId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Artist getArtistById(int artId) {
        try {
            Art art = db.findById(artId).get();
            return art.getArtist();
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}