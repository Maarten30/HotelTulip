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
public class MeetingRoom extends Room{

    private MeetingRoomType type;

    public MeetingRoom ( UUID id, MeetingRoomType type) {
        super(id);
        this.type = type;
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "type=" + type +
                '}';
    }
}
