package com.example.geohub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.geohub.model.City;

@Repository
public interface CityJpaRepository extends JpaRepository<City, Integer> {
    
}