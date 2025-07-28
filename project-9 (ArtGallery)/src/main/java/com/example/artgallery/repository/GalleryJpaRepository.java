package com.example.artgallery.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.artgallery.model.Gallery;

@Repository
public interface GalleryJpaRepository extends JpaRepository<Gallery, Integer>{
    
}