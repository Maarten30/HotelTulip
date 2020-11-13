package com.humanCompilers.hotelTulip.model;

public enum HotelRoomType {
    SINGLE("single"),
    DOUBLE("double"),
    TRIPLE("triple");

    private final String roomType;

    HotelRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }
}
