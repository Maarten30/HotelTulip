package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fakeReservationDao")
public class FakeReservationDataAccessService implements ReservationDao {

    private static List<Reservation> DB_Reservations = new ArrayList<>();

    @Override
    public int insertReservation(UUID id, Reservation reservation) {
        reservation.setId(id);
        DB_Reservations.add(reservation);
        return 1;
    }

    @Override
    public List<Reservation> selectAllReservations() {
        return DB_Reservations;
    }

    @Override
    public Reservation selectReservationById(UUID id) {
        return DB_Reservations.stream()
                .filter(reservation -> reservation.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int deleteReservationById(UUID id) {
        Reservation reservationMaybe = selectReservationById(id);

        if (reservationMaybe == null){
            return 0;
        }
        DB_Reservations.remove(reservationMaybe);
        return 1;
    }

    @Override
    public int updateReservationById(UUID id, Reservation updateReservation) {

        Reservation reservation = selectReservationById(id);

        // Comprobar que se ha enconctrado la reserva
        if(reservation == null) {
            return 0;
        }
        // Coger su index en el array
        int indexOfAreaToUpdate = DB_Reservations.indexOf(reservation);

        // Modificarlo
        if(indexOfAreaToUpdate >= 0) {
            DB_Reservations.set(indexOfAreaToUpdate, new Reservation(id, updateReservation.getCheckinDate(),
                    updateReservation.getCheckoutDate(), updateReservation.getReservedRoom(),
                    updateReservation.getTotalPrice()));
            return 1;
        } else {
            return 0;
        }
    }

}
