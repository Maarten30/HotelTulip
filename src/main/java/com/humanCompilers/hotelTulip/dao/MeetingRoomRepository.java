package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.MeetingRoom;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MeetingRoomRepository extends RoomBaseRepository<MeetingRoom> {
    
}
