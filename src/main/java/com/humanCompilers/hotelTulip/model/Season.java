package com.humanCompilers.hotelTulip.model;

/**
 * Clase que representa una temporada dentro de la aplicación
 * @author HumanCompilers
 */
public enum Season {
    HIGH("high"),
    MID("mid"),
    LOW("low");

    private final String season;

    /**
     * Constructor de la clase
     * @param season nombre de la temporada
     */
    Season(String season) {
        this.season = season;
    }

    public String getSeason() {
        return season;
    }
}
