package com.example.nxtstayz.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.model.Hotel;

@Repository
public interface RoomJpaRepository extends JpaRepository<Room, Integer> {
    List<Room> findByHotel(Hotel hotel);
}