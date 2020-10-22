package com.humanCompilers.hotelTulip.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Area {
    
    private UUID id;

    @NotNull
    private int capacity;
    @NotNull
    private double price;

    private Tipo type;


    public Area(@JsonProperty("id") UUID id, @JsonProperty("capacity") int capacity,
                @JsonProperty("price") double price, @JsonProperty("type") Tipo type) {
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        this.type = type;
    }

}
