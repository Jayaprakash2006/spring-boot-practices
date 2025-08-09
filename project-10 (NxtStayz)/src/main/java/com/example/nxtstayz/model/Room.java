package com.example.nxtstayz.model;

import javax.persistence.*;

@Entity
@Table(name="room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int roomId;

    @Column(name="roomnumber")
    private String roomNumber;

    @Column(name="type")
    private String roomType;

    @Column(name="price")
    private double price;

    @ManyToOne
    @JoinColumn(name="hotelid")
    private Hotel hotel;

    public Room() {}

    public Room(int roomId, String roomNumber, String roomType, double price, Hotel hotel) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.hotel = hotel;
    }

    public int getRoomId() {
        return this.roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return this.roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return this.hotel;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}