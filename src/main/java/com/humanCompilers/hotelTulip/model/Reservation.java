package com.humanCompilers.hotelTulip.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    private UUID id;
    private Date checkinDate;
    private Date checkoutDate;
    private Area reservedArea;

    public Reservation(@JsonProperty("id") UUID id, @JsonProperty("checkin") Date checkinDate,
                       @JsonProperty("checkout") Date checkoutDate, @JsonProperty("area") Area reservedArea) {
        this.id = id;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
        this.reservedArea = reservedArea;
    }

}
