package com.example.geohub.model;

import javax.persistence.*;

@Entity
@Table(name="city")
public class City {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cityid")
    private int cityId;

    @Column(name="cityname")
    private String cityName;

    @Column(name="population")
    private long population;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;

    @ManyToOne
    @JoinColumn(name="countryid")
    private Country country;

    public City() {}

    public City(int cityId, String cityName, long population, String latitude, String longitude, Country country) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }

    public int getCityId() {
        return this.cityId;
    }
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getPopulation() {
        return this.population;
    }
    public void setPopulation(long population) {
        this.population = population;
    }

    public String getLatitude() {
        return this.latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Country getCountry() {
        return this.country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
}