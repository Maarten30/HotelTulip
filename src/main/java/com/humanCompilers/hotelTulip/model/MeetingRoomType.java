package com.humanCompilers.hotelTulip.model;

public enum MeetingRoomType {

    grupoGrande("grupoGrande"),
    grupoReducido("grupoReducido"),
    conferencia("conferencia");

    private final String meetingRoomType;

    MeetingRoomType(String meetingRoomType) {
        this.meetingRoomType = meetingRoomType;
    }

    public String getMeetingRoomType() {
        return meetingRoomType;
    }
}
