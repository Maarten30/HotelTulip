package com.humanCompilers.hotelTulip.model;

/**
 * Clase que representa un tipo de sala dentro de la aplicación
 * @author HumanCompilers
 */
public enum MeetingRoomType {

    SMALL("SMALL"),
    MEDIUM("MEDIUM"),
    LARGE("LARGE");

    private final String meetingRoomType;

    /**
     * Constructor de la clase
     * @param meetingRoomType nombre del tipo de sala
     */
    MeetingRoomType(String meetingRoomType) {
        this.meetingRoomType = meetingRoomType;
    }

    public String getMeetingRoomType() {
        return meetingRoomType;
    }
}
