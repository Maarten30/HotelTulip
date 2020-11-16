package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.ReservationRepository;
import com.humanCompilers.hotelTulip.model.Reservation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ReservationServiceTest {

    // Clase sobre la que se han hecho tests
    static ReservationService reservationService;

    // Con el 'mock' simulas la creacion de las clases que no quieres usar pero que necesitas para hacer new de ReservationService
    static ReservationRepository reservationRepository = mock((ReservationRepository.class));
    static TarifaService tarifaService = mock(TarifaService.class);
    static RoomService roomService = mock(RoomService.class);

    static Reservation reservation_1;
    static Reservation reservation_2;


    // Inicilizas las varibles que vayas a necesitar
    @BeforeAll
    public static void init(){
        reservationService = new ReservationService(tarifaService, roomService, reservationRepository);

        reservation_1 = new Reservation();
        reservation_1.setCheckinDate(LocalDate.of(2020, 11, 17));
        reservation_1.setCheckoutDate(LocalDate.of(2020, 11, 30));
        reservation_1.setId(UUID.randomUUID());

        reservation_2 = new Reservation();
        reservation_2.setCheckinDate(LocalDate.of(2020, 12, 5));
        reservation_2.setCheckoutDate(LocalDate.of(2020, 12, 18));
        reservation_2.setId(UUID.randomUUID());
    }

    @Test
    void getAllReservations() {
        // Le dices que cuando llame al metodo 'reservationRepository.findAll()' devuelva una lista específica
        when(reservationRepository.findAll()).thenReturn(Stream.of(
                reservation_1, reservation_2
        ).collect(Collectors.toList()));

        assertEquals(2, reservationService.getAllReservations().size());
    }

    @Test
    void addReservation() {

        // Le dices que cuando llame al metodo 'reservationRepository.save()' devuelva un dato que tu elijas
        when(reservationRepository.save(any())).thenReturn(reservation_1);

        Reservation res = reservationService.addReservation(reservation_1);
        LocalDate fecha = res.getCheckinDate();

        assertEquals(reservation_1.getCheckinDate(), fecha);
    }

    @Test
    void prueba() {
        assertEquals("Hola", reservationService.prueba());
    }
}