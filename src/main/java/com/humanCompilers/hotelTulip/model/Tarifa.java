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

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Season season;
    private LocalDate starting_date;
    private LocalDate ending_date;
    private HotelRoomType room_type;
    private Double price;

    public Tarifa(@JsonProperty("season") Season season,
                  @JsonProperty("starting_date") LocalDate starting_date,
                  @JsonProperty("ending_date") LocalDate ending_date,
                  @JsonProperty("room_type") HotelRoomType room_type,
                  @JsonProperty("price") Double price) {
        this.season = season;
        this.starting_date = starting_date;
        this.ending_date = ending_date;
        this.room_type = room_type;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Tarifa{" +
                "season=" + season.getSeason() +
                ", starting_date=" + starting_date +
                ", ending_date=" + ending_date +
                ", room_type=" + room_type.getRoomType() +
                ", price=" + price +
                '}';
    }
}
