package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomDao {

    int insertRoom(UUID id, Room room);

    default int insertRoom(Room room) {
        UUID id = UUID.randomUUID();
        return insertRoom(id, room);
    }

    List<Room> selectAllRooms();

    Optional<Room> selectRoomById(UUID id);

    int deleteRoomById(UUID id);

    int updateRoomById(UUID id, Room room);
}
