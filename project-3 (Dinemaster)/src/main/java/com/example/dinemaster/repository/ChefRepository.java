package com.example.dinemaster.repository;

import java.util.ArrayList;
import com.example.dinemaster.model.Chef;
import com.example.dinemaster.model.Restaurant;

public interface ChefRepository {

    ArrayList<Chef> getChefs();
    Chef addChef(Chef chef);
    Chef getChefById(int id);
    Chef updateChef(int id, Chef chef);
    void deleteChef(int id);
    Restaurant getRestaurantByChefId(int chefId);

}