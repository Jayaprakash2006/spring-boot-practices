package com.example.artgallery.repository;

import java.util.List;
import java.util.ArrayList;
import com.example.artgallery.model.Art;
import com.example.artgallery.model.Artist;
import com.example.artgallery.model.Gallery;

public interface ArtistRepository {

    ArrayList<Artist> getArtists();
    Artist addArtist(Artist artist);
    Artist getArtist(int artistId);
    Artist updateArtist(int artistId, Artist artist);
    void deleteArtist(int artistId);
    List<Art> getArtistArts(int artistId);
    List<Gallery> getArtistGalleries(int artistId);

}