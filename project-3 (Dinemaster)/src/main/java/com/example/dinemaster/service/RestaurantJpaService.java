package com.example.dinemaster.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.repository.RestaurantRepository;
import com.example.dinemaster.repository.RestaurantJpaRepository;

@Service
public class RestaurantJpaService implements RestaurantRepository {

    @Autowired
    RestaurantJpaRepository db;

    public ArrayList<Restaurant> getRestaurants() {
        List<Restaurant> list = db.findAll();
        ArrayList<Restaurant> res_list = new ArrayList<>(list);
        return res_list;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return db.save(restaurant);
    }

    public Restaurant getRestaurantById(int id) {
        try {
            Restaurant restaurant = db.findById(id).get();
            return restaurant;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Restaurant updateRestaurant(int id, Restaurant restaurant) {
        try {
            Restaurant ext = db.findById(id).get();
            if(restaurant.getName() != null) ext.setName(restaurant.getName());
            if(restaurant.getAddress() != null) ext.setAddress(restaurant.getAddress());
            if(restaurant.getCuisineType() != null) ext.setCuisineType(restaurant.getCuisineType());
            if(restaurant.getRating() != 0) ext.setRating(restaurant.getRating());

            db.save(ext);
            return ext;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteRestaurant(int id) {
        try {
            db.deleteById(id);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}