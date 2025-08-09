package com.example.nxtstayz.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.repository.HotelJpaRepository;
import com.example.nxtstayz.repository.RoomRepository;
import com.example.nxtstayz.repository.RoomJpaRepository;

@Service
public class RoomJpaService implements RoomRepository {

    @Autowired
    RoomJpaRepository db;
    
    @Autowired
    HotelJpaRepository hotel_db;

    @Override
    public List<Room> getRooms() {
        return db.findAll();
    }

    @Override
    public Room addRoom(Room room) {
        try {
            int hotelId = room.getHotel().getHotelId();
            Hotel hotel = hotel_db.findById(hotelId).get();
            room.setHotel(hotel);
            return db.save(room);
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Room getRoom(int roomId) {
        try {
            return db.findById(roomId).get();
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Room updateRoom(int roomId, Room room) {
        try {
            Room ext = db.findById(roomId).get();
            if(room.getRoomNumber() != null) ext.setRoomNumber(room.getRoomNumber());
            if(room.getRoomType() != null) ext.setRoomType(room.getRoomType());
            if(room.getPrice() != 0.0) ext.setPrice(room.getPrice());
            if(room.getHotel() != null) {
                Hotel hotel = hotel_db.findById(room.getHotel().getHotelId()).get();
                ext.setHotel(hotel);
            }
            return db.save(ext);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteRoom(int roomId) {
        try {
            if(!db.existsById(roomId)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            db.deleteById(roomId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Hotel getHotelByRoom(int roomId) {
        try {
            Room room = db.findById(roomId).get();
            return room.getHotel();
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}