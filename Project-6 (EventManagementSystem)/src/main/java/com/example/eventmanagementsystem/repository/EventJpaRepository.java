package com.example.eventmanagementsystem.repository;

import java.util.List;
import com.example.eventmanagementsystem.model.Event;
import com.example.eventmanagementsystem.model.Sponsor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EventJpaRepository extends JpaRepository<Event, Integer> {
    List<Event> findBySponsors(Sponsor sponsor);
}