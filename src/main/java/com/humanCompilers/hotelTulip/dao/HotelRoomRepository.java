package com.humanCompilers.hotelTulip.dao;


import com.humanCompilers.hotelTulip.model.HotelRoom;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HotelRoomRepository extends RoomBaseRepository<HotelRoom> {
}
