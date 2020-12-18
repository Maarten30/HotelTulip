package com.humanCompilers.hotelTulip.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.UUID;

/**
 * Clase que representa una habitación de hotel dentro de la aplicación
 * @author HumanCompilers
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class HotelRoom extends Room {

    private HotelRoomType hotelRoomType;

    /**
     * Atributos empleados para la habitación de hotel
     * @param id identificativo de la habitación
     * @param hotelRoomType tipo de habitación
     */
    public HotelRoom (UUID id, HotelRoomType hotelRoomType) {
        super(id);
        this.hotelRoomType = hotelRoomType;
    }

    @Override
    public String toString() {
        return "HotelRoom{" +
                "type=" + hotelRoomType +
                '}';
    }
}
