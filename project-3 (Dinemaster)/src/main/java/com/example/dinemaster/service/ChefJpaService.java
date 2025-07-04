package com.example.dinemaster.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;
import com.example.dinemaster.model.Chef;
import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.repository.ChefJpaRepository;
import com.example.dinemaster.repository.RestaurantJpaRepository;
import com.example.dinemaster.repository.ChefRepository;

@Service
public class ChefJpaService implements ChefRepository {

    @Autowired
    ChefJpaRepository db;

    @Autowired
    RestaurantJpaRepository restaurant_db;

    public ArrayList<Chef> getChefs() {
        List<Chef> list = db.findAll();
        ArrayList arr_list = new ArrayList<>(list);
        return arr_list;
    }

    public Chef addChef(Chef chef) {
        try {
            int restaurantId = chef.getRestaurant().getId();
            Restaurant restaurant = restaurant_db.findById(restaurantId).get();
            chef.setRestaurant(restaurant);
            return db.save(chef);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Chef getChefById(int id) {
        try {
            Chef chef = db.findById(id).get();
            return chef;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Chef updateChef(int id, Chef chef) {
        try {
            Chef ext = db.findById(id).get();
            if(chef.getFirstName() != null) ext.setFirstName(chef.getFirstName());
            if(chef.getLastName() != null) ext.setLastName(chef.getLastName());
            if(chef.getExpertise() != null) ext.setExpertise(chef.getExpertise());
            if(chef.getExperienceYears() != 0) ext.setExperienceYears(chef.getExperienceYears());
            if(chef.getRestaurant() != null) {
                int restaurantId = chef.getRestaurant().getId();
                ext.setRestaurant(restaurant_db.findById(restaurantId).get());
            }

            db.save(ext);
            return ext;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteChef(int id) {
        try {
            db.deleteById(id);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public Restaurant getRestaurantByChefId(int chefId) {
        try {
            Chef chef = db.findById(chefId).get();
            Restaurant restaurant = chef.getRestaurant();
            return restaurant;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}