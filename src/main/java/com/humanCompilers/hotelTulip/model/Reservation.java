package com.humanCompilers.hotelTulip.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.humanCompilers.hotelTulip.service.TarifaService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    private UUID id;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Room reservedRoom;
    private Double totalPrice;

    @Autowired
    TarifaService tarifaService;

    public Reservation(@JsonProperty("id") UUID id, @JsonProperty("checkin") LocalDate checkinDate,
                       @JsonProperty("checkout") LocalDate checkoutDate, @JsonProperty("room") Room reservedRoom) {
        this.id = id;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
        this.reservedRoom = reservedRoom;
        this.totalPrice = calculateTotalPrice(checkinDate, checkoutDate, reservedRoom);
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

    public Double calculateTotalPrice(LocalDate starting_date, LocalDate ending_date, Room reservedRoom) {
        // AQUI SE HARIA LA LOGICA QUE CALCULARIA DE QUE TEMPORADA ES LA RESERVA, PARA
        // LUEGO IR A LA TARIFA ADECUADA Y CALCULAR BIEN EL PRECIO

        Double totalPrice = 0.0;

        Tarifa tarifa_actual = new Tarifa();
        //List<Tarifa> tarifas =  tarifaService.getAllTarifas();

        LocalDate starting_date_prueba = LocalDate.of(2020, 7, 1);
        LocalDate ending_date_prueba = LocalDate.of(2020, 7, 31);
        Tarifa tarifa_prueba = new Tarifa(starting_date_prueba, ending_date_prueba, RoomType.SINGLE, 50.0);
        Tarifa tarifa_prueba_doble = new Tarifa(starting_date_prueba, ending_date_prueba, RoomType.DOUBLE, 60.0);

        LocalDate starting_date1_prueba = LocalDate.of(2020, 8, 1);
        LocalDate ending_date1_prueba = LocalDate.of(2020, 10, 10);
        Tarifa tarifa1_prueba = new Tarifa(starting_date1_prueba, ending_date1_prueba, RoomType.SINGLE, 30.0);
        Tarifa tarifa1_prueba_doble = new Tarifa(starting_date1_prueba, ending_date1_prueba, RoomType.DOUBLE, 40.0);

        List<Tarifa> tarifas =  new ArrayList<>();

        tarifas.add(tarifa_prueba);
        tarifas.add(tarifa1_prueba);
        tarifas.add(tarifa_prueba_doble);
        tarifas.add(tarifa1_prueba_doble);


        while( starting_date.isBefore(ending_date)){

            // Saca la tarifa del dia
            tarifa_actual = calcularTarifa(starting_date, tarifas, reservedRoom);
            // Le suma al precio total, el precio de la tarifa de ese dia
            totalPrice += tarifa_actual.getPrice();
            // Le suma un dia al starting date, para analizar el siguiente dia
            starting_date = starting_date.plusDays(1);
            System.out.println(starting_date.toString());
        }

        return totalPrice;
    }

    public static Tarifa calcularTarifa(LocalDate date, List<Tarifa> tarifas, Room room) {

        // Saca la tarifa, mirando si la fecha esta entre las dos fechas que limitan la tarifa y
        // tambien comparando el tipo de habitacion
        Tarifa tarifa_sacada = tarifas.stream()
                .filter(t -> date.isAfter(t.getStarting_date()) &&
                        date.isBefore(t.getEnding_date())&&
                        t.getRoom_type().equals(room.getType()))
                .findFirst()
                .orElse(null);

        // Si es null, puede ser porque la fecha coincide justo con la fecha inicio o fin de la tarifa
        if(tarifa_sacada == null) {
            tarifa_sacada = tarifas.stream()
                    .filter(t -> (date.equals(t.getStarting_date()) ||
                            date.equals(t.getEnding_date())) &&
                            room.getType().equals(t.getRoom_type()))
                    .findFirst()
                    .orElse(null);
        }
        return tarifa_sacada;
    }

}
