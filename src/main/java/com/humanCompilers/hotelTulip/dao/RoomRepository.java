package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.Room;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz a través la cual se interactua con la parte de la base de datos relativa a las habitaciones y salas
 * @author HumanCompilers
 */
@Transactional
public interface RoomRepository extends RoomBaseRepository<Room> {
}
