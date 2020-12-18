package com.humanCompilers.hotelTulip.model;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clase que representa un tipo de habitación de hotel dentro de la aplicación
 * @author HumanCompilers
 */
public enum HotelRoomType {
    SINGLE("single"),
    DOUBLE("double"),
    TRIPLE("triple");

    private String roomType;

    /**
     * Constructor de la clase
     * @param roomType nombre del tipo de habitación de hotel
     */
    HotelRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }
}
