package com.humanCompilers.hotelTulip.model;

public enum MeetingRoomType {

    SMALL("grupoGrande"),
    MEDIUM("grupoReducido"),
    LARGE("conferencia");

    private final String meetingRoomType;

    MeetingRoomType(String meetingRoomType) {
        this.meetingRoomType = meetingRoomType;
    }

    public String getMeetingRoomType() {
        return meetingRoomType;
    }
}
