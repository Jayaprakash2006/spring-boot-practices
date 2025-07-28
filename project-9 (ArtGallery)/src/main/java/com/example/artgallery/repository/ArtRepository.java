package com.example.artgallery.repository;

import java.util.List;
import java.util.ArrayList;
import com.example.artgallery.model.Art;
import com.example.artgallery.model.Artist;

public interface ArtRepository {

    ArrayList<Art> getArts();
    Art addArt(Art art);
    Art getArt(int artId);
    Art updateArt(int artId, Art art);
    void deleteArt(int artId);
    Artist getArtistById(int artId);

}