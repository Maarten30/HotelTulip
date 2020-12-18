package com.humanCompilers.hotelTulip.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.UUID;

/**
 * Clase que representa una sala dentro de la aplicación
 * @author HumanCompilers
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class MeetingRoom extends Room{

    private MeetingRoomType meetingRoomType;

    /**
     * Atributos empleados para la sala
     * @param id identificativo de la sala
     * @param meetingRoomType tipo de sala
     */
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
