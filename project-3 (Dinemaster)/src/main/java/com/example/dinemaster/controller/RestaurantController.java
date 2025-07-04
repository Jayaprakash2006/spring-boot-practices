package com.example.dinemaster.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.service.RestaurantJpaService;

@RestController
public class RestaurantController {

    @Autowired
    RestaurantJpaService service;

    @GetMapping("/restaurants")
    public ArrayList<Restaurant> getRestaurants() {
        return service.getRestaurants();
    }

    @PostMapping("/restaurants")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return service.addRestaurant(restaurant);
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurantById(@PathVariable("id") int id) {
        return service.getRestaurantById(id);
    }

    @PutMapping("/restaurants/{id}")
    public Restaurant updateRestaurant(@PathVariable("id") int id, @RequestBody Restaurant restaurant) {
        return service.updateRestaurant(id, restaurant);
    }

    @DeleteMapping("/restaurants/{id}")
    public void deleteRestaurant(@PathVariable("id") int id) {
        service.deleteRestaurant(id);
    }
}