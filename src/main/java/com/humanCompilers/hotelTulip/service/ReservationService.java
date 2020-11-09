package com.humanCompilers.hotelTulip.service;


import com.humanCompilers.hotelTulip.dao.ReservationDao;
import com.humanCompilers.hotelTulip.model.Reservation;
import com.humanCompilers.hotelTulip.model.Room;
import com.humanCompilers.hotelTulip.model.RoomType;
import com.humanCompilers.hotelTulip.model.Tarifa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationDao reservationDao;
    private final TarifaService tarifaService;
    private final RoomService roomService;

    @Autowired
    public ReservationService(@Qualifier("fakeReservationDao") ReservationDao reservationDao,
                              TarifaService tarifaService,
                              RoomService roomService) {
        this.reservationDao = reservationDao;
        this.tarifaService = tarifaService;
        this.roomService = roomService;
    }

    public int addReservation(Reservation reservation) {
        return reservationDao.insertReservation(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationDao.selectAllReservations();
    }

    public Reservation getReservationById(UUID id) {
        return reservationDao.selectReservationById(id);
    }

    public int deleteReservationById(UUID id) {
        return reservationDao.deleteReservationById(id);
    }

    public int updateReservationById(UUID id, Reservation newReservation) {
        return reservationDao.updateReservationById(id, newReservation);
    }

    public Double calculateTotalPrice(LocalDate starting_date, LocalDate ending_date, Room reservedRoom) {

        Double totalPrice = 0.0;

        Tarifa tarifa_actual = new Tarifa();

        List<Tarifa> tarifas =  tarifaService.getAllTarifas();

        while( starting_date.isBefore(ending_date)){
            // Saca la tarifa del dia
            tarifa_actual = tarifaService.calcularTarifa(starting_date, tarifas, reservedRoom);
            // Le suma al precio total, el precio de la tarifa de ese dia
            totalPrice += tarifa_actual.getPrice();
            // Le suma un dia al starting date, para analizar el siguiente dia
            starting_date = starting_date.plusDays(1);
            System.out.println(starting_date.toString());
        }

        return totalPrice;
    }

    public Room CheckAvailability(LocalDate checkin, LocalDate checkOut, RoomType roomType) {

        List<Room> rooms = roomService.getAllRooms();
        System.out.println(rooms);
        List<Reservation> reservations = getAllReservations();
        System.out.println(reservations);
        Room freeRoom = null;

        List<Room> rooms_cloned = new ArrayList<>();
        rooms.forEach(room -> {
            Room aux_room = new Room();
            aux_room.setId(room.getId());
            aux_room.setType(room.getType());
            rooms_cloned.add(aux_room);
        });

        rooms_cloned.removeIf(r -> r.getType() != roomType);

        for (Room room:rooms_cloned) {
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

        return freeRoom;
    }
}
