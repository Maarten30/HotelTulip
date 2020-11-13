package com.humanCompilers.hotelTulip.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation {

    // Cuando hagamos la parte de usuarios igual podemos añadirle a esta clase un nombre de usuario

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkinDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkoutDate;

    @ManyToOne
    private Room reservedRoom;
    private Double totalPrice;

    public Reservation( UUID id, LocalDate checkinDate, LocalDate checkoutDate, Room reservedRoom) {
        this.id = id;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
        this.reservedRoom = reservedRoom;
    }

    public Reservation(UUID id, LocalDate checkinDate, LocalDate checkoutDate, Room reservedRoom, Double totalPrice) {
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
