package com.example.eventmanagementsystem.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.eventmanagementsystem.model.Event;
import com.example.eventmanagementsystem.model.Sponsor;

public interface EventRepository {

    ArrayList<Event> getEvents();
    Event addEvent(Event event);
    Event getEvent(int eventId);
    Event updateEvent(int eventId, Event event);
    void deleteEvent(int eventId);
    List<Sponsor> getSponsorsByEvent(int eventId);
}