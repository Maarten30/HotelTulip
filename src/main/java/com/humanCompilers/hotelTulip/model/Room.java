package com.humanCompilers.hotelTulip.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Room {

    @Id
    private UUID id;

    public Room (UUID id) {
        this.id = id;

    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id  +
                '}';
    }
}
