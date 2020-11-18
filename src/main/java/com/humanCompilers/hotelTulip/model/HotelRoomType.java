package com.humanCompilers.hotelTulip.model;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum HotelRoomType {
    SINGLE("single"),
    DOUBLE("double"),
    TRIPLE("triple");

    private String roomType;

    HotelRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }
}
