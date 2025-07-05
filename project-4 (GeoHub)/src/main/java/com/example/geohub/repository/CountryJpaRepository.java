package com.example.geohub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.geohub.model.Country;

@Repository
public interface CountryJpaRepository extends JpaRepository<Country, Integer> {
    
}