package com.humanCompilers.hotelTulip.dao;


import com.humanCompilers.hotelTulip.model.Reservation;
import com.humanCompilers.hotelTulip.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Interfaz a través la cual se interactua con la parte de la base de datos relativa a las reservas. Los métodos añadidos
 * manualmente se autoimplementan sin necesidad de crear otra clase.
 * @author HumanCompilers
 */
public interface ReservationRepository extends CrudRepository<Reservation, UUID> {

    List<Reservation> findAllByUser(User user);
}
