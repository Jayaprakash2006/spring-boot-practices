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
import com.example.eventmanagementsystem.model.Sponsor;
import com.example.eventmanagementsystem.model.Event;
import com.example.eventmanagementsystem.service.SponsorJpaService;

@RestController
public class SponsorController {

    @Autowired
    SponsorJpaService service;

    @GetMapping("/events/sponsors")
    public ArrayList<Sponsor> getSponsors() {
        return service.getSponsors();
    }

    @PostMapping("/events/sponsors")
    public Sponsor addSponsor(@RequestBody Sponsor sponsor) {
        return service.addSponsor(sponsor);
    }

    @GetMapping("/events/sponsors/{sponsorId}")
    public Sponsor getSponsor(@PathVariable("sponsorId") int sponsorId) {
        return service.getSponsor(sponsorId);
    }

    @PutMapping("/events/sponsors/{sponsorId}")
    public Sponsor updateSponsor(@PathVariable("sponsorId") int sponsorId, @RequestBody Sponsor sponsor) {
        return service.updateSponsor(sponsorId, sponsor);
    }

    @DeleteMapping("events/sponsors/{sponsorId}")
    public void deleteSponsor(@PathVariable("sponsorId") int sponsorId) {
        service.deleteSponsor(sponsorId);
    }

    @GetMapping("/sponsors/{sponsorId}/events")
    public List<Event> getSponsorEvents(@PathVariable("sponsorId") int sponsorId) {
        return service.getSponsorEvents(sponsorId);
    }
}