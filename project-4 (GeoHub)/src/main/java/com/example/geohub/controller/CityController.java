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
import com.example.geohub.model.City;
import com.example.geohub.model.Country;
import com.example.geohub.service.CityJpaService;

@RestController
public class CityController {

    @Autowired
    CityJpaService service;

    @GetMapping("/countries/cities")
    public ArrayList<City> getCities() {
        return service.getCities();
    }

    @PostMapping("/countries/cities")
    public City addCity(@RequestBody City city) {
        return service.addCity(city);
    }

    @GetMapping("/countries/cities/{cityId}")
    public City getCityById(@PathVariable("cityId") int cityId) {
        return service.getCityById(cityId);
    }

    @PutMapping("/countries/cities/{cityId}")
    public City updateCity(@PathVariable("cityId") int cityId, @RequestBody City city) {
        return service.updateCity(cityId, city);
    }

    @DeleteMapping("/countries/cities/{cityId}")
    public void deleteCity(@PathVariable("cityId") int cityId) {
        service.deleteCity(cityId);
    }

    @GetMapping("/cities/{cityId}/country")
    public Country getCountry(@PathVariable("cityId") int cityId) {
        return service.getCountry(cityId);
    }
}