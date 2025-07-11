package com.example.wordlyweek.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.example.wordlyweek.model.Magazine;
import com.example.wordlyweek.model.Writer;

@Repository
public interface MagazineJpaRepository extends JpaRepository<Magazine, Integer> {
    List<Magazine> findByWriters(Writer writer);
}