package com.humanCompilers.hotelTulip.dao;


import com.humanCompilers.hotelTulip.model.HotelRoom;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz a través la cual se interactua con la parte de la base de datos relativa a las habitaciones
 * @author HumanCompilers
 */
@Transactional
public interface HotelRoomRepository extends RoomBaseRepository<HotelRoom> {
}
