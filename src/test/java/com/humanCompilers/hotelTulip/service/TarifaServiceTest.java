package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.ReservationRepository;
import com.humanCompilers.hotelTulip.dao.TarifaRepository;
import com.humanCompilers.hotelTulip.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Clase que realiza el testeo de la lógica de negocio relacionada con las tarifas de las habitaciones
 * @author Human Compilers
 */
@ExtendWith(SpringExtension.class)
class TarifaServiceTest {

    // Clase sobre la que se han hecho tests
    static TarifaService tarifaService;

    // Con el 'mock' simulas la creacion de las clases que no quieres usar pero que necesitas para hacer new de ReservationService
    static TarifaRepository tarifaRepository = mock((TarifaRepository.class));

    static Tarifa tarifa_1;
    static Tarifa tarifa_2;

    static HotelRoom room;

    /**
     * Método que sirve para inicializar todas las variables a utilizar en la clase
     */
    @BeforeAll
    public static void init() {
        tarifaService = new TarifaService(tarifaRepository);

        tarifa_1 = new Tarifa();
        tarifa_1.setStarting_date(LocalDate.of(2021, 9, 20));
        tarifa_1.setEnding_date(LocalDate.of(2021, 9, 23));
        tarifa_1.setPrice(200.0);
        tarifa_1.setRoom_type(HotelRoomType.DOUBLE);
        tarifa_1.setSeason(Season.HIGH);

        tarifa_2 = new Tarifa();
        tarifa_2.setStarting_date(LocalDate.of(2021, 11, 20));
        tarifa_2.setEnding_date(LocalDate.of(2021, 11, 23));
        tarifa_2.setPrice(250.0);
        tarifa_2.setRoom_type(HotelRoomType.SINGLE);
        tarifa_2.setSeason(Season.HIGH);

        room = new HotelRoom();
        room.setHotelRoomType(HotelRoomType.DOUBLE);
    }

    /**
     * Método que sirve para comprobar que el método para obtener todas las tarifas de habitación de la base de datos
     * funciona correctamente
     */
    @Test
    void getAllTarifas() {

        when(tarifaRepository.findAll()).thenReturn(Stream.of(
                tarifa_1, tarifa_2
        ).collect(Collectors.toList()));

        assertEquals(2, tarifaService.getAllTarifas().size());
    }

    /**
     * Método que sirve para comprobar si el método que guarda la tarifa de una habitación en la base de datos
     * funciona correctamente
     */
    @Test
    void addTarifa() {

        when(tarifaRepository.save(any())).thenReturn(tarifa_1);

        Tarifa tarifa = tarifaService.addTarifa(tarifa_1);
        LocalDate fecha = tarifa.getStarting_date();

        assertEquals(tarifa_1.getStarting_date(), fecha);
    }

    /**
     * Método que sirve para comprobar que el método para cálcular la tarifa de una sala funciona correctamente
     */
    @Test
    void calculateHotelRoomTarifa() {
        when(tarifaRepository.findAll()).thenReturn(Stream.of(
                tarifa_1, tarifa_2
        ).collect(Collectors.toList()));

        Tarifa tarifResult = tarifaService.calculateHotelRoomTarifa(LocalDate.of
                (2021, 9, 22), room);

        assertEquals(tarifa_1,tarifResult);
    }
}