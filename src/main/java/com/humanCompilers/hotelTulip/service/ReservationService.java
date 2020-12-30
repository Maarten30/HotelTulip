package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.ReservationRepository;
import com.humanCompilers.hotelTulip.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Clase que proporciona la lógica de negocio relacionada con las reservas
 * @HumanCompilers
 */
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TarifaService tarifaService;
    private final TarifaMeetingRoomService tarifaMeetingRoomService;
    private final RoomService roomService;

    /**
     * Constructor de la clase
     * @param tarifaService Instancia de la clase tarifaService para poder hacerle llamadas
     * @param tarifaMeetingRoomService Instancia de la clase tarifaMeetingRoomService para poder hacerle llamadas
     * @param roomService Instancia de la clase roomService para poder hacerle llamadas
     * @param reservationRepository Instancia de la clase reservationRepository para poder hacerle llamadas
     */
    @Autowired
    public ReservationService(TarifaService tarifaService,
                              TarifaMeetingRoomService tarifaMeetingRoomService,
                              RoomService roomService,
                              ReservationRepository reservationRepository) {
        this.tarifaService = tarifaService;
        this.tarifaMeetingRoomService = tarifaMeetingRoomService;
        this.roomService = roomService;
        this.reservationRepository = reservationRepository;
    }


    /**
     * Método que sirve para guardar una reserva en la base de datos
     * @param reservation reserva a guardar
     */
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    /**
     * Método que sirve para obtener todas las reservas existentes de la base de datos
     * @return lista que contiene todas las reservas existentes
     */
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations =  new ArrayList<>();
        // Parse 'Iterable' to 'List'
        Iterable<Reservation> reservas_db = reservationRepository.findAll();
        reservas_db.forEach(r -> {
            reservations.add(r);
        });
        return reservations;
    }

    /**
     * Método que sirve para encontrar una reserva por Id de la base de datos
     * @param id código identificativo de la reserva
     * @return Devuelve la reserva correspondiente al código identificativo
     */
    public Reservation getReservationById(UUID id) { return reservationRepository.findById(id).get(); }

    /**
     * Método que sirve para eliminar una reserva de la base de datos sabiendo su Id
     * @param id Id de la reserva a eliminar
     */
    public int deleteReservationById(UUID id) { reservationRepository.deleteById(id); return 1; }

    /**
     * Método que sirve para eliminar todas las reservas de la base de datos
     */
    public int deleteAllReservations() { reservationRepository.deleteAll(); return 1; }

    /**
     * Método para modificar una reserva de la base de datos
     * @param id identificador de la reserva a modificar
     * @param newReservation nueva reserva a guardar
     */
    public int updateReservationById(UUID id, Reservation newReservation) {
        // Get
        Reservation db_reservation = getReservationById(id);
        // Update
        db_reservation.setTotalPrice(newReservation.getTotalPrice());
        db_reservation.setReservedRoom(newReservation.getReservedRoom());
        db_reservation.setCheckinDate(newReservation.getCheckinDate());
        db_reservation.setCheckoutDate(newReservation.getCheckoutDate());
        // Save Updated
        reservationRepository.save(db_reservation);
        return 1;
    }

    /**
     * Método que sirve para calcular el precio total de una reserva
     * @param starting_date Fecha de comienzo de la reserva
     * @param ending_date Fecha final de la reserva
     * @param reservedRoom Habitación de hotel a reservar
     * @return Devuelve el precio total de la reserva
     */
    public Double calculateTotalPrice(LocalDate starting_date, LocalDate ending_date, HotelRoom reservedRoom) {

        boolean noErrorsInReservation = true;
        Double totalPrice = 0.0;
        Tarifa tarifa_actual;

        if(starting_date.isAfter(ending_date)) return null;

        while( starting_date.isBefore(ending_date)){
            // Saca la tarifa del dia
            tarifa_actual = tarifaService.calculateHotelRoomTarifa(starting_date, reservedRoom);
            // Le suma al precio total, el precio de la tarifa de ese dia
            if(tarifa_actual != null) {
                totalPrice += tarifa_actual.getPrice();
            } else {
                noErrorsInReservation = false;
                break;
            }
            // Le suma un dia al starting date, para analizar el siguiente dia
            starting_date = starting_date.plusDays(1);
        }
        if(noErrorsInReservation){
            return totalPrice;
        } else {
            return null;
        }
    }

    /**
     * Método que sirve para calcular el precio total de la reserva
     * @param starting_date Fecha de comienzo de la reserva
     * @param ending_date Fecha final de la reserva
     * @param reservedRoom Sala a reservar
     * @return Devuelve el precio total de la reserva
     */
    public Double calculateMeetingRoomTotalPrice(LocalDate starting_date, LocalDate ending_date, MeetingRoom reservedRoom) {

        boolean noErrorsInReservation = true;
        Double totalPrice = 0.0;
        TarifaMeetingRoom tarifa_actual;
        List<TarifaMeetingRoom> tarifas =  tarifaMeetingRoomService.getAllTarifasMeetingRoom();

        while( starting_date.isBefore(ending_date)){
            // Saca la tarifa del dia
            tarifa_actual = tarifaMeetingRoomService.calculateMeetingRoomTarifa(starting_date, reservedRoom);
            // Le suma al precio total, el precio de la tarifa de ese dia
            if(tarifa_actual != null) {
                totalPrice += tarifa_actual.getPrice();
            } else {
                noErrorsInReservation = false;
                break;
            }

            // Le suma un dia al starting date, para analizar el siguiente dia
            starting_date = starting_date.plusDays(1);
        }
        if(noErrorsInReservation){
            return totalPrice;
        } else {
            return null;
        }

    }

    /**
     * Método que sirve para comprobar la disponibilidad de una habitación para unas fechas específicas
     * @param checkin Fecha de entrada
     * @param checkOut Fecha de salida
     * @param hotelRoomType Tipo de habitación a reservar
     * @return Devuelve información sobre si la habitación está disponible o no
     */
    public HotelRoom CheckHotelRoomAvailability(LocalDate checkin, LocalDate checkOut, HotelRoomType hotelRoomType) {

        // Se podría mejorar cogiendo solo las habitaciones del tipo buscado
        List<HotelRoom> rooms = roomService.getAllHotelRooms();
        List<Reservation> reservations = getAllReservations();

        HotelRoom freeRoom = null;

        List<HotelRoom> rooms_cloned = new ArrayList<>();
        rooms.forEach(room -> {
            HotelRoom aux_room = new HotelRoom();
            aux_room.setId(room.getId());
            aux_room.setHotelRoomType(room.getHotelRoomType());
            rooms_cloned.add(aux_room);
        });

        rooms_cloned.removeIf(r -> r.getHotelRoomType() != hotelRoomType);

        if (rooms_cloned.size() > 0) {
            if(reservations.size() > 0) {
                for (HotelRoom room:rooms_cloned) {
                    boolean occupied = false;
                    UUID id = room.getId();

                    for (Reservation reservation:reservations) {
                        if(reservation.getReservedRoom().getId().equals(id))
                            if(!(checkin.isAfter(reservation.getCheckoutDate()) ||
                                    checkOut.isBefore(reservation.getCheckinDate()))){
                                occupied = true;
                            } else {
                                occupied = false;
                                break;
                            }
                    }
                    if(!occupied) {
                        return room;
                    }
                }
            } else {
                // Revisar este 'else'
                freeRoom = rooms_cloned.stream().findFirst().orElse(null);
            }
        }


        return freeRoom;
    }

    /**
     * Método que sirve para conocer la disponibilidad de una sala en unas fechas concretas
     * @param checkin Fecha de entrada
     * @param checkOut Fecha de salida
     * @param meetingRoomType Tipo de sala a reservar
     * @return Devuelve información sobre si la sala está disponible o no
     */
    public MeetingRoom CheckMeetingRoomAvailability(LocalDate checkin, LocalDate checkOut, MeetingRoomType meetingRoomType) {

        List<MeetingRoom> rooms = roomService.getAllMeetingRooms();
        List<Reservation> reservations = getAllReservations();
        MeetingRoom freeRoom = null;

        List<MeetingRoom> rooms_cloned = new ArrayList<>();
        rooms.forEach(room -> {
            MeetingRoom aux_room = new MeetingRoom();
            aux_room.setId(room.getId());
            aux_room.setMeetingRoomType(room.getMeetingRoomType());
            rooms_cloned.add(aux_room);
        });

        rooms_cloned.removeIf(r -> r.getMeetingRoomType() != meetingRoomType);

        if (rooms_cloned.size() > 0) {
            if(reservations.size() > 0) {
                for (MeetingRoom room:rooms_cloned) {
                    boolean occupied = false;
                    UUID id = room.getId();

                    for (Reservation reservation:reservations) {
                        if(reservation.getReservedRoom().getId().equals(id))
                            if(!(checkin.isAfter(reservation.getCheckoutDate()) ||
                                    checkOut.isBefore(reservation.getCheckinDate()))){
                                occupied = true;
                            } else {
                                occupied = false;
                                break;
                            }
                    }
                    if(!occupied) {
                        freeRoom = room;
                        break;
                    }
                }
            } else {
                freeRoom = rooms_cloned.stream().findFirst().orElse(null);
            }
        }

        return freeRoom;
    }

    /**
     * Método que sirve para conseguir una reserva de la base de datos corresponiente a un usuario concreto
     * @param user Usuario del que se desea conocer las reservas
     * @return Devuelve la lista de reservas corresponientes al usuario
     */
    public List<Reservation> getReservationsByUser(User user){

        List<Reservation> reservations = reservationRepository.findAllByUser(user);
        return reservations;

    }

}
