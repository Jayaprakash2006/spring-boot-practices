package com.example.nxttrendz2.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nxttrendz2.model.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {
    
}