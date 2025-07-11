package com.example.wordlyweek.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.example.wordlyweek.model.Writer;
import com.example.wordlyweek.model.Magazine;

@Repository
public interface WriterJpaRepository extends JpaRepository<Writer, Integer> {
    List<Writer> findByMagazines(Magazine magazine);
}