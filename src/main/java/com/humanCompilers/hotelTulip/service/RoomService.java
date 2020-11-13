package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.RoomDao;
import com.humanCompilers.hotelTulip.model.HotelRoom;
import com.humanCompilers.hotelTulip.model.MeetingRoom;
import com.humanCompilers.hotelTulip.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {

    private final RoomDao roomDao;

    @Autowired
    public RoomService(@Qualifier("fakeRoomDao") RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    // HotelRoom service

    public int addHotelRoom(HotelRoom room) {
        return 0;
    }

    public int addHotelRoom(UUID id, HotelRoom room) {
        return 0;
    }

    public List<HotelRoom> getAllHotelRooms() {
       // return roomDao.selectAllRooms();
        return null;
    }

    public Room getHotelRoomById(UUID id) {
        return null;
    }

    public int deleteHotelRoomById(UUID id) {
        return 0;
    }

    public int updateHotelRoomById(UUID id, HotelRoom newRoom) {
        return 0;
    }

    // MeetingRoom service

    public int addMeetingRoom(MeetingRoom room) {
        return 0;
    }

    public int addMeetingRoom(UUID id, MeetingRoom room) {
        return 0;
    }

    public List<MeetingRoom> getAllMeetingRooms() {
       // return roomDao.selectAllRooms();
        return null;
    }

    public Room getMeetingRoomById(UUID id) {
        return null;
    }

    public int deleteMeetingRoomById(UUID id) {
        return 0;
    }

    public int updateMeetingRoomById(UUID id, MeetingRoom newRoom) {
        return 0;
    }
}
