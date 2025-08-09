package com.example.nxtstayz.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.ArrayList;
import java.util.ArrayList;
import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.service.RoomJpaService;

@RestController
public class RoomController {

    @Autowired
    RoomJpaService service;

    @GetMapping("/hotels/rooms")
    public List<Room> getRooms() {
        return service.getRooms();
    }

    @PostMapping("/hotels/rooms")
    public Room addRoom(@RequestBody Room room) {
        return service.addRoom(room);
    }

    @GetMapping("/hotels/rooms/{roomId}")
    public Room getRoom(@PathVariable("roomId") int roomId) {
        return service.getRoom(roomId);
    }

    @PutMapping("/hotels/rooms/{roomId}")
    public Room updateRoom(@PathVariable("roomId") int roomId, @RequestBody Room room) {
        return service.updateRoom(roomId, room);
    }

    @DeleteMapping("/hotels/rooms/{roomId}")
    public void deleteRoom(@PathVariable("roomId") int roomId) {
        service.deleteRoom(roomId);
    }

    @GetMapping("/rooms/{roomId}/hotel")
    public Hotel getHotelByRoom(@PathVariable("roomId") int roomId) {
        return service.getHotelByRoom(roomId);
    }
}