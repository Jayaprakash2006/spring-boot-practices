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
import com.example.dinemaster.model.Chef;
import com.example.dinemaster.model.Restaurant;
import com.example.dinemaster.service.ChefJpaService;

@RestController
public class ChefController {

    @Autowired
    ChefJpaService service;

    @GetMapping("/restaurants/chefs")
    public ArrayList<Chef> getChefs() {
        return service.getChefs();
    }

    @PostMapping("/restaurants/chefs")
    public Chef addChef(@RequestBody Chef chef) {
        return service.addChef(chef);
    }

    @GetMapping("/restaurants/chefs/{id}")
    public Chef getChefById(@PathVariable("id") int id) {
        return service.getChefById(id);
    }

    @PutMapping("/restaurants/chefs/{id}")
    public Chef updateChef(@PathVariable("id") int id, @RequestBody Chef chef) {
        return service.updateChef(id, chef);
    }

    @DeleteMapping("/restaurants/chefs/{id}")
    public void deleteChef(@PathVariable("id") int id) {
        service.deleteChef(id);
    }

    @GetMapping("/chefs/{chefId}/restaurant")
    public Restaurant getRestaurantByChefId(@PathVariable("chefId") int chefId) {
        return service.getRestaurantByChefId(chefId);
    }
}