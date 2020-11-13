package com.humanCompilers.hotelTulip.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class HotelRoom extends Room {

    private HotelRoomType hotelRoomType;

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
