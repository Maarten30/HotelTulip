package com.humanCompilers.hotelTulip.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class MeetingRoom {

    private UUID id;
    private MeetingRoomType type;

    public MeetingRoom (UUID id, MeetingRoomType type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
