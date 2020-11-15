package com.humanCompilers.hotelTulip.model;

public enum MeetingRoomType {

    SMALL("SMALL"),
    MEDIUM("MEDIUM"),
    LARGE("LARGE");

    private final String meetingRoomType;

    MeetingRoomType(String meetingRoomType) {
        this.meetingRoomType = meetingRoomType;
    }

    public String getMeetingRoomType() {
        return meetingRoomType;
    }
}
