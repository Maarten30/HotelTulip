package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.MeetingRoom;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz a través la cual se interactua con la parte de la base de datos relativa a las salas
 * @author HumanCompilers
 */
@Transactional
public interface MeetingRoomRepository extends RoomBaseRepository<MeetingRoom> {
    
}
