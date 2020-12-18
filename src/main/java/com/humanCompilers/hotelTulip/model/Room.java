package com.humanCompilers.hotelTulip.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * Clase padre de habitación y sala dentro de la aplicaicón
 * @author HumanCompilers
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Room {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    /**
     * Constructor de la clase
     * @param id identificador de la habitación o sala
     */
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
