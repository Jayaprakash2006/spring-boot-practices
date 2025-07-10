package com.example.eventmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.NoSuchElementException;
import com.example.eventmanagementsystem.model.Sponsor;
import com.example.eventmanagementsystem.model.Event;
import com.example.eventmanagementsystem.repository.SponsorRepository;
import com.example.eventmanagementsystem.repository.SponsorJpaRepository;
import com.example.eventmanagementsystem.repository.EventJpaRepository;

@Service
public class SponsorJpaService implements SponsorRepository {

    @Autowired
    SponsorJpaRepository db;

    @Autowired
    EventJpaRepository event_db;

    @Override
    public ArrayList<Sponsor> getSponsors() {
        return (ArrayList<Sponsor>) db.findAll();
    }

    @Override
    public Sponsor addSponsor(Sponsor sponsor) {
        try {
            List<Event> events = sponsor.getEvents();
            List<Integer> eventsId = new ArrayList<>();

                for(Event event: events) {
                    eventsId.add(event.getEventId());
                }
                List<Event> new_events = event_db.findAllById(eventsId);
                
                for(Event event:new_events) {
                    event.getSponsors().add(sponsor);
                }

                if(eventsId.size() != new_events.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some id's are missing.");
                }
                sponsor.setEvents(new_events);
                Sponsor saved =  db.save(sponsor);
                event_db.saveAll(new_events);
           


            return saved;

        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Sponsor getSponsor(int sponsorId) {
        try {
            return db.findById(sponsorId).get();
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Sponsor updateSponsor(int sponsorId, Sponsor sponsor) {
        try {
            Sponsor ext = db.findById(sponsorId).get();
            if(sponsor.getSponsorName() != null) ext.setSponsorName(sponsor.getSponsorName());
            if(sponsor.getIndustry() != null) ext.setIndustry(sponsor.getIndustry());
            
            if(sponsor.getEvents() != null) {
                List<Event> events = sponsor.getEvents();
                List<Integer> eventsId = new ArrayList<>();

                for(Event event: events) {
                    eventsId.add(event.getEventId());
                }

                List<Event> new_events = event_db.findAllById(eventsId);

                if(eventsId.size() != new_events.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some id's not found");
                }

                for(Event event: ext.getEvents()) {
                    event.getSponsors().remove(ext);
                }

                for(Event event:new_events) {
                    event.getSponsors().add(ext);
                }

                sponsor.setEvents(new_events);
                
                ext.setEvents(new_events);
                db.save(ext);
                event_db.saveAll(new_events);
            }
            return db.save(ext);
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    
    public void deleteSponsor(int sponsorId) {
        try {
            Sponsor sponsor = db.findById(sponsorId).get();
            List<Event> events = event_db.findBySponsors(sponsor);
            for(Event event: events) {
                event.getSponsors().remove(sponsor);
            }
            event_db.saveAll(events);
            db.deleteById(sponsorId);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public List<Event> getSponsorEvents(int sponsorId) {
        try {
            Sponsor sponsor = db.findById(sponsorId).get();
            List<Event> events = event_db.findBySponsors(sponsor);
            return events;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}