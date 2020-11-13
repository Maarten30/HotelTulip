package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.Room;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface RoomRepository extends RoomBaseRepository<Room> {
}
