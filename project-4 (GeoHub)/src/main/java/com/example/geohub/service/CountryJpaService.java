package com.example.geohub.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import com.example.geohub.model.Country;
import com.example.geohub.repository.CountryJpaRepository;
import com.example.geohub.repository.CountryRepository;

@Service
public class CountryJpaService implements CountryRepository {

    @Autowired
    CountryJpaRepository db;

    public ArrayList<Country> getCountries() {
        List<Country> list = db.findAll();
        ArrayList<Country> arr_list = new ArrayList<>(list);
        return arr_list;
    }

    public Country addCountry(Country country) {
        return db.save(country);
    }

    public Country getCountryById(int countryId) {
        try {
            Country country = db.findById(countryId).get();
            return country;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Country updateCountry(int countryId, Country country) {
        try {
            Country ext = db.findById(countryId).get();
            if(country.getCountryName() != null) ext.setCountryName(country.getCountryName());
            if(country.getCurrency() != null) ext.setCurrency(country.getCurrency());
            if(country.getPopulation() != 0) ext.setPopulation(country.getPopulation());
            if(country.getLatitude() != null) ext.setLatitude(country.getLatitude());
            if(country.getLongitude() != null) ext.setLongitude(country.getLongitude());

            return db.save(ext);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteCountry(int countryId) {
        try {
            db.deleteById(countryId);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}