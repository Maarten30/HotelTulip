package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeRoomDao")
public class FakeRoomDataAccessService implements RoomDao {

    private static List<Room> DB = new ArrayList<>();

    @Override
    public int insertRoom(UUID id, Room room) {
        DB.add(new Room(id, room.getType()));
        return 1;
    }

    @Override
    public List<Room> selectAllRooms() {
        return DB;
    }

    @Override
    public Optional<Room> selectRoomById(UUID id) {
        return DB.stream()
                .filter(room -> room.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteRoomById(UUID id) {
        Optional<Room> roomMaybe = selectRoomById(id);

        if (!(roomMaybe.isPresent())){
            return 0;
        }
        DB.remove(roomMaybe.get());
        return 1;
    }

    @Override
    public int updateRoomById(UUID id, Room updateRoom) {
        return selectRoomById(id)
                .map(room -> {
                    int indexOfRoomToUpdate = DB.indexOf(room);
                    if(indexOfRoomToUpdate >= 0) {
                        DB.set(indexOfRoomToUpdate, new Room(id, updateRoom.getType()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
