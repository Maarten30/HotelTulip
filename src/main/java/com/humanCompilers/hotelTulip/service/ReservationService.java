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

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TarifaService tarifaService;
    private final RoomService roomService;

    @Autowired
    public ReservationService(TarifaService tarifaService,
                              RoomService roomService,
                              ReservationRepository reservationRepository) {
        this.tarifaService = tarifaService;
        this.roomService = roomService;
        this.reservationRepository = reservationRepository;
    }


    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations =  new ArrayList<>();
        // Parse 'Iterable' to 'List'
        Iterable<Reservation> reservas_db = reservationRepository.findAll();
        System.out.println(reservas_db);
        reservas_db.forEach(r -> {
            reservations.add(r);
        });
        return reservations;
    }

    public Reservation getReservationById(UUID id) { return reservationRepository.findById(id).get(); }

    public int deleteReservationById(UUID id) { reservationRepository.deleteById(id); return 1; }

    public int deleteAllReservations() { reservationRepository.deleteAll(); return 1; }

    // Si hace falta esto se podria descomponer en un metodo por atributo a actualizar
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

    public Double calculateTotalPrice(LocalDate starting_date, LocalDate ending_date, HotelRoom reservedRoom) {

        boolean noErrorsInReservation = true;
        Double totalPrice = 0.0;
        Tarifa tarifa_actual;

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

    public HotelRoom CheckHotelRoomAvailability(LocalDate checkin, LocalDate checkOut, HotelRoomType hotelRoomType) {

        List<HotelRoom> rooms = roomService.getAllHotelRooms();
        System.out.println(rooms);
        List<Reservation> reservations = getAllReservations();
        System.out.println(reservations);
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

    public List<Reservation> getReservationsByUser(User user){

        List<Reservation> reservations = reservationRepository.findAllByUser(user);
        System.out.println(reservations);
        return reservations;

    }

}
