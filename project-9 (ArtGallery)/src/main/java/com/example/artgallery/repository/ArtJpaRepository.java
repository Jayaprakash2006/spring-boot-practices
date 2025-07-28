package com.example.artgallery.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.example.artgallery.model.Art;
import com.example.artgallery.model.Artist;

@Repository
public interface ArtJpaRepository extends JpaRepository<Art, Integer> {
    List<Art> findByArtist(Artist artist);
}