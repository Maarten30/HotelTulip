package com.humanCompilers.hotelTulip.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Tarifa {

    private LocalDate starting_date;
    private LocalDate ending_date;
    private RoomType room_type;
    private Double price;

    public Tarifa(LocalDate starting_date, LocalDate ending_date, RoomType room_type, Double price) {
        this.starting_date = starting_date;
        this.ending_date = ending_date;
        this.room_type = room_type;
        this.price = price;
    }
}
