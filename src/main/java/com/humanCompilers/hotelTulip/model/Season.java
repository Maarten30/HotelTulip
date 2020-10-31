package com.humanCompilers.hotelTulip.model;

public enum Season {
    HIGH("high"),
    MID("mid"),
    LOW("low");

    private final String season;

    Season(String season) {
        this.season = season;
    }

    public String getSeason() {
        return season;
    }
}
