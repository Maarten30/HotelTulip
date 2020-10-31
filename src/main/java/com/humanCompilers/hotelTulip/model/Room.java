package com.humanCompilers.hotelTulip.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Room {

    private UUID id;
    private RoomType type;

    public Room (UUID id, RoomType type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
