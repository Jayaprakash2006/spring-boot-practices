package com.example.dinemaster.model;

import javax.persistence.*;

@Entity
@Table(name="restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="cuisinetype")
    private String cuisineType;

    @Column(name="rating")
    private int rating;

    public Restaurant() {}

    public Restaurant(int id, String name, String address, String cuisineType, int rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cuisineType = cuisineType;
        this.rating = rating;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCuisineType() {
        return this.cuisineType;
    }
    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public int getRating() {
        return this.rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
}