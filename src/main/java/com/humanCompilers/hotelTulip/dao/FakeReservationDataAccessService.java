package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.Reservation;
import com.humanCompilers.hotelTulip.model.Room;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fakeReservationDao")
public class FakeReservationDataAccessService implements ReservationDao {

    private static List<Reservation> DB_Reservations = new ArrayList<>();

    @Override
    public int insertReservation(UUID id, Reservation reservation) {
        return 0;
    }

    @Override
    public List<Reservation> selectAllReservations() {
        return DB_Reservations;
    }

    @Override
    public Optional<Reservation> selectReservationById(UUID id) {
        return DB_Reservations.stream()
                .filter(reservation -> reservation.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteReservationById(UUID id) {
        Optional<Reservation> reservationMaybe = selectReservationById(id);

        if (!(reservationMaybe.isPresent())){
            return 0;
        }
        DB_Reservations.remove(reservationMaybe.get());
        return 1;
    }

    @Override
    public int updateReservationById(UUID id, Reservation updateReservation) {
        return selectReservationById(id)
                .map(reservation -> {
                    int indexOfAreaToUpdate = DB_Reservations.indexOf(reservation);
                    if(indexOfAreaToUpdate >= 0) {
                        DB_Reservations.set(indexOfAreaToUpdate, new Reservation(id, updateReservation.getCheckinDate(),
                                updateReservation.getCheckoutDate(), updateReservation.getReservedRoom(),
                                updateReservation.getTotalPrice()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

}
