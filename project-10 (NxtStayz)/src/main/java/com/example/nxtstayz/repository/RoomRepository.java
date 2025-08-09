package com.example.nxtstayz.repository;

import java.util.List;
import java.util.ArrayList;
import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;

public interface RoomRepository {

    List<Room> getRooms();
    Room addRoom(Room room);
    Room getRoom(int roomId);
    Room updateRoom(int roomId, Room room);
    void deleteRoom(int roomId);
    Hotel getHotelByRoom(int roomId);

}