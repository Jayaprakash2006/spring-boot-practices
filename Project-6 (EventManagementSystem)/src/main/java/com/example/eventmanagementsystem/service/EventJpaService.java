package com.example.eventmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import com.example.eventmanagementsystem.model.Event;
import com.example.eventmanagementsystem.model.Sponsor;
import com.example.eventmanagementsystem.repository.EventRepository;
import com.example.eventmanagementsystem.repository.EventJpaRepository;
import com.example.eventmanagementsystem.repository.SponsorJpaRepository;

@Service
public class EventJpaService implements EventRepository {

    @Autowired
    EventJpaRepository db;

    @Autowired
    SponsorJpaRepository sponsor_db;

    @Override
    public ArrayList<Event> getEvents() {
        return (ArrayList<Event>) db.findAll();
    }

    @Override
    public Event addEvent(Event event) {
        try {
            List<Sponsor> sponsors = event.getSponsors();
            List<Integer> sponsorsId = new ArrayList<>();
            for(Sponsor sponsor: sponsors) {
                sponsorsId.add(sponsor.getSponsorId());
            }
            List<Sponsor> new_sponsors = sponsor_db.findAllById(sponsorsId);

            event.setSponsors(new_sponsors);
            return db.save(event);
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Event getEvent(int eventId) {
        try {
            Event event = db.findById(eventId).get();
            return event;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Event updateEvent(int eventId, Event event) {
        try {
            Event ext = db.findById(eventId).get();
            if(event.getEventName() != null) ext.setEventName(event.getEventName());
            if(event.getDate() != null) ext.setDate(event.getDate());
            if(event.getSponsors() != null) {
                ext.setSponsors(null);
                List<Sponsor> sponsors = event.getSponsors();
                List<Integer> sponsorsId = new ArrayList<>();
                for(Sponsor sponsor: sponsors) {
                    sponsorsId.add(sponsor.getSponsorId());
                }
                List<Sponsor> new_sponsors = sponsor_db.findAllById(sponsorsId);
                ext.setSponsors(new_sponsors);
            }

            return db.save(ext);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteEvent(int eventId) {
        try {
            Event event = db.findById(eventId).get();
            List<Sponsor> sponsors = sponsor_db.findByEvents(event);
            for(Sponsor sponsor: sponsors) {
                sponsor.getEvents().remove(event);
            }
            sponsor_db.saveAll(sponsors);
            db.deleteById(eventId);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Sponsor> getSponsorsByEvent(int eventId) {
        try {
            Event event = db.findById(eventId).get();
            List<Sponsor> sponsors = sponsor_db.findByEvents(event);
            return sponsors;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}