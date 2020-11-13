package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.Room;
import com.humanCompilers.hotelTulip.model.HotelRoomType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeRoomDao")
public class FakeRoomDataAccessService implements RoomDao {

    private static List<Room> DB = new ArrayList<>();

    public FakeRoomDataAccessService() {
        /*Room room1 = new Room(UUID.randomUUID(), HotelRoomType.SINGLE);
        Room room2 = new Room(UUID.randomUUID(), HotelRoomType.DOUBLE);
        Room room3 = new Room(UUID.randomUUID(), HotelRoomType.TRIPLE);
        Room room4 = new Room(UUID.randomUUID(), HotelRoomType.SINGLE);
        Room room5 = new Room(UUID.randomUUID(), HotelRoomType.DOUBLE);
        Room room6 = new Room(UUID.randomUUID(), HotelRoomType.TRIPLE);

        DB.add(room1);
        DB.add(room2);
        DB.add(room3);
        DB.add(room4);
        DB.add(room5);
        DB.add(room6);*/

    }

    @Override
    public int insertRoom(UUID id, Room room) {
        /*DB.add(new Room(id, room.getType()));*/
        return 1;
    }

    @Override
    public List<Room> selectAllRooms() {
        return DB;
    }

    @Override
    public Room selectRoomById(UUID id) {
        return DB.stream()
                .filter(room -> room.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int deleteRoomById(UUID id) {
        Room roomMaybe = selectRoomById(id);

        if (roomMaybe == null) {
            return 0;
        } else {
            DB.remove(roomMaybe);
        }

        return 1;
    }

    @Override
    public int updateRoomById(UUID id, Room updateRoom) {

        /*Room aux = selectRoomById(id);
        int indexOfRoomToUpdate = DB.indexOf(aux);
        if (indexOfRoomToUpdate >= 0) {
            DB.set(indexOfRoomToUpdate, new Room(id, updateRoom.getType()));
            return 1;
        } else {
            return 0;
        }*/
        return 0;
    }
}
