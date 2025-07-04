package com.example.geohub.repository;

import com.example.geohub.model.Country;
import java.util.ArrayList;

public interface CountryRepository {

    ArrayList<Country> getCountries();
    Country addCountry(Country country);
    Country getCountryById(int countryId);
    Country updateCountry(int countryId, Country country);
    void deleteCountry(int countryId);

}