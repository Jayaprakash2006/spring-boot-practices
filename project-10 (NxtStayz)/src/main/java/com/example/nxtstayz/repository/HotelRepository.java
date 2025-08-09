package com.example.nxtstayz.repository;

import java.util.List;
import java.util.ArrayList;
import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;

public interface HotelRepository {

    List<Hotel> getHotels();
    Hotel addHotel(Hotel hotel);
    Hotel getHotel(int hotelId);
    Hotel updateHotel(int hotelId, Hotel hotel);
    void deleteHotel(int hotelId);
    List<Room> getRoomByHotel(int hotelId);

}