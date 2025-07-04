package com.example.nxttrendz1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nxttrendz1.model.Review;

@Repository
public interface ReviewJpaRepository extends JpaRepository<Review, Integer> {
    
}