package com.example.nxtstayz.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.service.HotelJpaService;

@RestController
public class HotelController {

    @Autowired
    private HotelJpaService service;

    @GetMapping("/hotels")
    public List<Hotel> getHotels() {
        return service.getHotels();
    }

    @PostMapping("/hotels")
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return service.addHotel(hotel);
    }

    @GetMapping("/hotels/{hotelId}")
    public Hotel getHotel(@PathVariable("hotelId") int hotelId) {
        return service.getHotel(hotelId);
    }

    @PutMapping("/hotels/{hotelId}")
    public Hotel updateHotel(@PathVariable("hotelId") int hotelId, @RequestBody Hotel hotel) {
        return service.updateHotel(hotelId, hotel);
    }

    @DeleteMapping("/hotels/{hotelId}")
    public void deleteHotel(@PathVariable("hotelId") int hotelId) {
        service.deleteHotel(hotelId);
    }

    @GetMapping("/hotels/{hotelId}/rooms")
    public List<Room> getRoomByHotel(@PathVariable("hotelId") int hotelId) {
        return service.getRoomByHotel(hotelId);
    }

}