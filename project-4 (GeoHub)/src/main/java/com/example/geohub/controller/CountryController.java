package com.example.geohub.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import com.example.geohub.model.Country;
import com.example.geohub.service.CountryJpaService;

@RestController
public class CountryController {

    @Autowired
    CountryJpaService service;

    @GetMapping("/countries")
    public ArrayList<Country> getCountries() {
        return service.getCountries();
    }

    @PostMapping("/countries")
    public Country addCountry(@RequestBody Country country) {
        return service.addCountry(country);
    }

    @GetMapping("/countries/{countryId}")
    public Country getCountryById(@PathVariable("countryId") int countryId) {
        return service.getCountryById(countryId);
    }

    @PutMapping("countries/{countryId}")
    public Country updateCountry(@PathVariable("countryId") int countryId, @RequestBody Country country) {
        return service.updateCountry(countryId, country);
    }

    @DeleteMapping("/countries/{countryId}")
    public void deleteCountry(@PathVariable("countryId") int countryId) {
        service.deleteCountry(countryId);
    }
}