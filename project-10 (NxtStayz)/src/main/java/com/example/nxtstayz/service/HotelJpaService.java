package com.example.nxtstayz.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.repository.HotelRepository;
import com.example.nxtstayz.repository.HotelJpaRepository;
import com.example.nxtstayz.repository.RoomJpaRepository;

@Service
public class HotelJpaService implements HotelRepository {

    @Autowired
    HotelJpaRepository db;

    @Autowired
    RoomJpaRepository room_db;

    @Override
    public List<Hotel> getHotels() {
        return db.findAll();
    }

    @Override
    public Hotel addHotel(Hotel hotel) {
        return db.save(hotel);
    }

    @Override
    public Hotel getHotel(int hotelId) {
        try {
            return db.findById(hotelId).get();
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Hotel updateHotel(int hotelId, Hotel hotel) {
        try {
            Hotel ext = db.findById(hotelId).get();
            if(hotel.getHotelName() != null) ext.setHotelName(hotel.getHotelName());
            if(hotel.getLocation() != null) ext.setLocation(hotel.getLocation());
            if(hotel.getRating() != 0) ext.setRating(hotel.getRating());

            return db.save(ext);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteHotel(int hotelId) {
        try {
            if(!db.existsById(hotelId)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            List<Room> rooms = room_db.findByHotel(db.findById(hotelId).get());
            for(Room room: rooms) {
                room.setHotel(null);
            }
            room_db.saveAll(rooms);
            db.deleteById(hotelId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Room> getRoomByHotel(int hotelId) {
        try {
            Hotel hotel = db.findById(hotelId).get();
            List<Room> rooms = room_db.findByHotel(hotel);
            return rooms;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}