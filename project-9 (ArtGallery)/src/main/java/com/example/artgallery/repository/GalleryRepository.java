package com.example.artgallery.repository;

import java.util.List;
import java.util.ArrayList;
import com.example.artgallery.model.Gallery;
import com.example.artgallery.model.Artist;

public interface GalleryRepository {

    ArrayList<Gallery> getGalleries();
    Gallery addGallery(Gallery gallery);
    Gallery getGallery(int galleryId);
    Gallery updateGallery(int galleryId, Gallery gallery);
    void deleteGallery(int galleryId);
    List<Artist> getGalleryArtists(int galleryId);

}