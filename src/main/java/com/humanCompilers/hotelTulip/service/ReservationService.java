package com.humanCompilers.hotelTulip.service;


import com.humanCompilers.hotelTulip.dao.ReservationDao;
import com.humanCompilers.hotelTulip.dao.ReservationRepository;
import com.humanCompilers.hotelTulip.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationDao reservationDao;
    private final ReservationRepository reservationRepository;
    private final TarifaService tarifaService;
    private final RoomService roomService;

    @Autowired
    public ReservationService(@Qualifier("fakeReservationDao") ReservationDao reservationDao,
                              TarifaService tarifaService,
                              RoomService roomService, ReservationRepository reservationRepository) {
        this.reservationDao = reservationDao;
        this.tarifaService = tarifaService;
        this.roomService = roomService;
        this.reservationRepository = reservationRepository;
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

    public Double calculateTotalPrice(LocalDate starting_date, LocalDate ending_date, HotelRoom reservedRoom) {

        Double totalPrice = 0.0;

        Tarifa tarifa_actual = new Tarifa();

        List<Tarifa> tarifas =  tarifaService.getAllTarifas();

        while( starting_date.isBefore(ending_date)){
            // Saca la tarifa del dia
            tarifa_actual = tarifaService.calculateHotelRoomTarifa(starting_date, tarifas, reservedRoom);
            // Le suma al precio total, el precio de la tarifa de ese dia
            totalPrice += tarifa_actual.getPrice();
            // Le suma un dia al starting date, para analizar el siguiente dia
            starting_date = starting_date.plusDays(1);
            System.out.println(starting_date.toString());
        }

        return totalPrice;
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

        return freeRoom;
    }
}
