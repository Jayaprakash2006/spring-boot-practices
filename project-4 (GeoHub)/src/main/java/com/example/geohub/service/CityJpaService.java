package com.example.geohub.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import com.example.geohub.model.City;
import com.example.geohub.model.Country;
import com.example.geohub.repository.CityJpaRepository;
import com.example.geohub.repository.CountryJpaRepository;
import com.example.geohub.repository.CityRepository;

@Service
public class CityJpaService implements CityRepository {

    @Autowired
    CityJpaRepository db;

    @Autowired
    CountryJpaRepository country_db;

    public ArrayList<City> getCities() {
        return (ArrayList<City>) db.findAll();
    }

    public City addCity(City city) {
        try {
            int countryId = city.getCountry().getCountryId();
            city.setCountry(country_db.findById(countryId).get());
            return db.save(city);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public City getCityById(int cityId) {
        try {
            return db.findById(cityId).get();
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public City updateCity(int cityId, City city) {
        try {
            City ext = db.findById(cityId).get();
            if(city.getCityName() != null) ext.setCityName(city.getCityName());
            if(city.getPopulation() != 0) ext.setPopulation(city.getPopulation());
            if(city.getLatitude() != null) ext.setLatitude(city.getLatitude());
            if(city.getLongitude() != null) ext.setLongitude(city.getLongitude());
            if(city.getCountry() != null) {
                int countryId = city.getCountry().getCountryId();
                ext.setCountry(country_db.findById(countryId).get());
            }

            return db.save(ext);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteCity(int cityId) {
        try {
            db.deleteById(cityId);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public Country getCountry(int cityId) {
        try {
            Country country = db.findById(cityId).get().getCountry();
            return country;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}