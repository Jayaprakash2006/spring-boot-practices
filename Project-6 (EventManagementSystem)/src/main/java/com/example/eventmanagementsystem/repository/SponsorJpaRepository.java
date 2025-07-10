package com.example.eventmanagementsystem.repository;

import java.util.List;
import com.example.eventmanagementsystem.model.Sponsor;
import com.example.eventmanagementsystem.model.Event;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SponsorJpaRepository extends JpaRepository<Sponsor, Integer> {
    List<Sponsor> findByEvents(Event event);
}