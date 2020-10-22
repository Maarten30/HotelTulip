package com.humanCompilers.hotelTulip.service;


import com.humanCompilers.hotelTulip.dao.ReservationDao;
import com.humanCompilers.hotelTulip.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationDao reservationDao;

    @Autowired
    public ReservationService(@Qualifier("fakeReservationDao") ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public int addReservation(Reservation reservation) {
        return reservationDao.insertReservation(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationDao.selectAllReservations();
    }

    public Optional<Reservation> getReservationById(UUID id) {
        return reservationDao.selectReservationById(id);
    }

    public int deleteReservationById(UUID id) {
        return reservationDao.deleteReservationById(id);
    }

    public int updateReservationById(UUID id, Reservation newReservation) {
        return reservationDao.updateReservationById(id, newReservation);
    }
}
