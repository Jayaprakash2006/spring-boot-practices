package com.example.eventmanagementsystem.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import com.example.eventmanagementsystem.model.Event;
import com.example.eventmanagementsystem.model.Sponsor;
import com.example.eventmanagementsystem.service.EventJpaService;

@RestController
public class EventController {

    @Autowired
    EventJpaService service;

    @GetMapping("/events")
    public ArrayList<Event> getEvents() {
        return service.getEvents();
    }

    @PostMapping("/events")
    public Event addEvent(@RequestBody Event event) {
        return service.addEvent(event);
    }

    @GetMapping("/events/{eventId}")
    public Event getEvent(@PathVariable("eventId") int eventId) {
        return service.getEvent(eventId);
    }

    @PutMapping("/events/{eventId}")
    public Event updateEvent(@PathVariable("eventId") int eventId, @RequestBody Event event) {
        return service.updateEvent(eventId, event);
    }

    @DeleteMapping("/events/{eventId}")
    public void deleteEvent(@PathVariable("eventId") int eventId) {
        service.deleteEvent(eventId);
    }

    @GetMapping("/events/{eventId}/sponsors")
    public List<Sponsor> getSponsorsByEvent(@PathVariable("eventId") int eventId) {
        return service.getSponsorsByEvent(eventId);
    }
}