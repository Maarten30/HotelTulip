package com.humanCompilers.hotelTulip.model;

public enum RoomType {
    SINGLE("single"),
    DOUBLE("double"),
    TRIPLE("triple");

    private final String roomType;

    RoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }
}
