package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.ReservationRepository;
import com.humanCompilers.hotelTulip.model.*;
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

/**
 * Clase que realiza el testeo de la lógica de negocio relacionada con reservas
 * @author Human Compilers
 */
@ExtendWith(SpringExtension.class)
class ReservationServiceTest {

    // Clase sobre la que se han hecho tests
    static ReservationService reservationService;

    // Con el 'mock' simulas la creacion de las clases que no quieres usar pero que necesitas para hacer new de ReservationService
    static ReservationRepository reservationRepository = mock((ReservationRepository.class));
    static TarifaService tarifaService = mock(TarifaService.class);
    static TarifaMeetingRoomService tarifaMeetingRoomService = mock(TarifaMeetingRoomService.class);
    static RoomService roomService = mock(RoomService.class);

    static Reservation reservation_1;
    static Reservation reservation_2;

    static HotelRoom hotelRoom_1;
    static HotelRoom hotelRoom_2;


    /**
     * Método que sirve para inicializar todas las variables a utilizar en la clase
     */
    @BeforeAll
    public static void init(){
        // Reservation Service
        reservationService = new ReservationService(tarifaService, tarifaMeetingRoomService, roomService, reservationRepository);

        // Room 1
        hotelRoom_1 = new HotelRoom();
        hotelRoom_1.setHotelRoomType(HotelRoomType.SINGLE);
        hotelRoom_1.setId(UUID.randomUUID());
        // Room 2
        hotelRoom_2 = new HotelRoom();
        hotelRoom_2.setHotelRoomType(HotelRoomType.DOUBLE);
        hotelRoom_2.setId(UUID.randomUUID());

        // Reservation 1
        reservation_1 = new Reservation();
        reservation_1.setCheckinDate(LocalDate.of(2020, 11, 17));
        reservation_1.setCheckoutDate(LocalDate.of(2020, 11, 30));
        reservation_1.setReservedRoom(hotelRoom_1);
        reservation_1.setId(UUID.randomUUID());
        // Reservation 2
        reservation_2 = new Reservation();
        reservation_2.setCheckinDate(LocalDate.of(2020, 12, 5));
        reservation_2.setCheckoutDate(LocalDate.of(2020, 12, 18));
        reservation_2.setReservedRoom(hotelRoom_2);
        reservation_2.setId(UUID.randomUUID());
    }

    /**
     * Método que sirve para testear si todas las reservas se reciben correctamente de la base de datos
     */
    @Test
    void getAllReservations() {
        // Le dices que cuando llame al metodo 'reservationRepository.findAll()' devuelva una lista específica
        when(reservationRepository.findAll()).thenReturn(Stream.of(
                reservation_1, reservation_2
        ).collect(Collectors.toList()));

        assertEquals(2, reservationService.getAllReservations().size());
    }

    /**
     * Método que sirve para testear que las reservas se guardan correctamente
     */
    @Test
    void addReservation() {

        // Le dices que cuando llame al metodo 'reservationRepository.save()' devuelva un dato que tu elijas
        when(reservationRepository.save(any())).thenReturn(reservation_1);

        Reservation res = reservationService.addReservation(reservation_1);
        LocalDate fecha = res.getCheckinDate();

        assertEquals(reservation_1.getCheckinDate(), fecha);
    }

    /**
     * Método que testea si el cálculo del precio total de una reserva de una habitación se hace correctamente
     */
    @Test
    void calculateTotalPrice() {

        Tarifa tarifa = new Tarifa();
        tarifa.setPrice(120.00);
        when(tarifaService.calculateHotelRoomTarifa(any(), any())).thenReturn(tarifa);

        Double TotalPrice = reservationService.calculateTotalPrice(LocalDate.of(2020, 12, 13),
                LocalDate.of(2020, 12, 17), new HotelRoom());

        assertEquals(TotalPrice, 480.0, 0.0);
    }

    /**
     * Método que sirve para testear que el precio total de una reserva de una habitación se calcula correctamente si las fechas
     * insertadas en la aplicación no siguen un orden lógico en el tiempo
     */
    @Test
    void calculateTotalPriceInverseDates() {

        Tarifa tarifa = new Tarifa();
        tarifa.setPrice(120.00);
        when(tarifaService.calculateHotelRoomTarifa(any(), any())).thenReturn(tarifa);

        Double TotalPrice = reservationService.calculateTotalPrice(LocalDate.of(2020, 12, 17),
                LocalDate.of(2020, 12, 13), new HotelRoom());

        assertEquals(null, TotalPrice);
    }

    /**
     * Método que testea si el cálculo del precio total de una reserva de una sala se hace correctamente
     */
    @Test
    void calculateMeetingRoomTotalPrice() {
        TarifaMeetingRoom  tarifa = new TarifaMeetingRoom();
        tarifa.setPrice(120.00);
        when(tarifaMeetingRoomService.calculateMeetingRoomTarifa(any(), any())).thenReturn(tarifa);

        Double TotalPrice = reservationService.calculateMeetingRoomTotalPrice(LocalDate.of(2020, 12, 13),
                LocalDate.of(2020, 12, 17), new MeetingRoom());

        assertEquals( 480.0, TotalPrice, 0.0);
    }

    /**
     * Método que sirve para testear si una habitación de hotel está disponible para las fechas solicitadas
     */
    @Test
    void CheckHotelRoomAvailability() {

        when(roomService.getAllHotelRooms()).thenReturn(Stream.of(
                hotelRoom_1, hotelRoom_2
        ).collect(Collectors.toList()));

        when(reservationService.getAllReservations()).thenReturn(Stream.of(
                reservation_1, reservation_2
        ).collect(Collectors.toList()));

        HotelRoom hotelRoom = reservationService.CheckHotelRoomAvailability(LocalDate.of(2020, 12, 2),
                LocalDate.of(2020, 12, 4), HotelRoomType.DOUBLE);

       assertEquals(hotelRoom_2.getId(), hotelRoom.getId());

    }
}