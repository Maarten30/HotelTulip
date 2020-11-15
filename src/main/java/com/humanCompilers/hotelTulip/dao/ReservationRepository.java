package com.humanCompilers.hotelTulip.dao;


import com.humanCompilers.hotelTulip.model.Reservation;
import com.humanCompilers.hotelTulip.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends CrudRepository<Reservation, UUID> {

    List<Reservation> findAllByUser(User user);
}
