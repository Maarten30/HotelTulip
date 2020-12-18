package com.humanCompilers.hotelTulip.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Clase que representa una tarifa de sala dentro de la aplicación
 * @author HumanCompilers
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class TarifaMeetingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Season season;
    private LocalDate starting_date;
    private LocalDate ending_date;
    private MeetingRoomType room_type;
    private Double price;

    /**
     * Atributos que tiene la tarifa de sala
     * @param season temporada a la que pertenece la tarifa
     * @param starting_date fecha de comienzo de la tarifa
     * @param ending_date fecha de finalización de la tarifa
     * @param room_type tipo de sala a la que afecta la tarifa
     * @param price precio
     */
    public TarifaMeetingRoom(@JsonProperty("season") Season season,
                  @JsonProperty("starting_date") LocalDate starting_date,
                  @JsonProperty("ending_date") LocalDate ending_date,
                  @JsonProperty("room_type") MeetingRoomType room_type,
                  @JsonProperty("price") Double price) {
        this.season = season;
        this.starting_date = starting_date;
        this.ending_date = ending_date;
        this.room_type = room_type;
        this.price = price;
    }

    @Override
    public String toString() {
        return "TarifaMeetingRoom{" +
                "id=" + id +
                ", season=" + season +
                ", starting_date=" + starting_date +
                ", ending_date=" + ending_date +
                ", room_type=" + room_type +
                ", price=" + price +
                '}';
    }
}
