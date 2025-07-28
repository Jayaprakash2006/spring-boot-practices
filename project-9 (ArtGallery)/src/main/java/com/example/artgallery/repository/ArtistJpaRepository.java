package com.example.artgallery.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.artgallery.model.Art;
import com.example.artgallery.model.Artist;
import com.example.artgallery.model.Gallery;
import java.util.List;

@Repository
public interface ArtistJpaRepository extends JpaRepository<Artist, Integer> {
    List<Artist> findByGalleries(Gallery gallery);
    List<Artist> findByArts(Art art);
}