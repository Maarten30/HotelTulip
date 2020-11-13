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

    private MeetingRoomType meetingRoomType;

    public MeetingRoom ( UUID id, MeetingRoomType meetingRoomType) {
        super(id);
        this.meetingRoomType = meetingRoomType;
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "type=" + meetingRoomType +
                '}';
    }
}
