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

@ExtendWith(SpringExtension.class)
class TarifaServiceTest {

    // Clase sobre la que se han hecho tests
    static TarifaService tarifaService;

    // Con el 'mock' simulas la creacion de las clases que no quieres usar pero que necesitas para hacer new de ReservationService
    static TarifaRepository tarifaRepository = mock((TarifaRepository.class));

    static Tarifa tarifa_1;
    static Tarifa tarifa_2;

    static HotelRoom room;


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

    @Test
    void getAllTarifas() {

        when(tarifaRepository.findAll()).thenReturn(Stream.of(
                tarifa_1, tarifa_2
        ).collect(Collectors.toList()));

        assertEquals(2, tarifaService.getAllTarifas().size());
    }

    @Test
    void addTarifa() {

        when(tarifaRepository.save(any())).thenReturn(tarifa_1);

        Tarifa tarifa = tarifaService.addTarifa(tarifa_1);
        LocalDate fecha = tarifa.getStarting_date();

        assertEquals(tarifa_1.getStarting_date(), fecha);
    }

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