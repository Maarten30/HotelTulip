package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.RoomDao;
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

    public int addRoom(Room room) {
        return roomDao.insertRoom(room);
    }

    public int addRoom(UUID id, Room room) {
        return roomDao.insertRoom(id, room);
    }

    public List<Room> getAllRooms() {
        return roomDao.selectAllRooms();
    }

    public Room getRoomById(UUID id) {
        return roomDao.selectRoomById(id);
    }

    public int deleteRoomById(UUID id) {
        return roomDao.deleteRoomById(id);
    }

    public int updateRoomById(UUID id, Room newRoom) {
        return roomDao.updateRoomById(id, newRoom);
    }
}
