package com.humanCompilers.hotelTulip.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    // Cuando hagamos la parte de usuarios igual podemos añadirle a esta clase un nombre de usuario

    private UUID id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkinDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkoutDate;
    private Room reservedRoom;
    private Double totalPrice;

    public Reservation(@JsonProperty("id") UUID id, @JsonProperty("checkin") LocalDate checkinDate,
                       @JsonProperty("checkout") LocalDate checkoutDate, @JsonProperty("room") Room reservedRoom) {
        this.id = id;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
        this.reservedRoom = reservedRoom;
    }

    public Reservation(@JsonProperty("id") UUID id, @JsonProperty("checkin") LocalDate checkinDate,
                       @JsonProperty("checkout") LocalDate checkoutDate, @JsonProperty("room") Room reservedRoom,
                       @JsonProperty("totalPrice") Double totalPrice) {
        this.id = id;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
        this.reservedRoom = reservedRoom;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                ", reservedRoom=" + reservedRoom +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
