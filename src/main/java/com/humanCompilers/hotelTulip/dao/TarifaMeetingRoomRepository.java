package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.TarifaMeetingRoom;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz a través la cual se interactua con la parte de la base de datos relativa a las taarifas de salas
 * @author HumanCompilers
 */
public interface TarifaMeetingRoomRepository extends CrudRepository<TarifaMeetingRoom, Integer> {
}
