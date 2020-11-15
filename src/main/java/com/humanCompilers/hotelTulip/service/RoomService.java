package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.HotelRoomRepository;
import com.humanCompilers.hotelTulip.dao.MeetingRoomRepository;
import com.humanCompilers.hotelTulip.dao.RoomRepository;
import com.humanCompilers.hotelTulip.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRoomRepository hotelRoomRepository;
    private final MeetingRoomRepository meetingRoomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository,
                       HotelRoomRepository hotelRoomRepository,
                       MeetingRoomRepository meetingRoomRepository) {
        this.roomRepository = roomRepository;
        this.hotelRoomRepository = hotelRoomRepository;
        this.meetingRoomRepository = meetingRoomRepository;
    }

    // PARENT ROOM SERVICE
    public int addRooms(List<Room> rooms) {
        roomRepository.saveAll(rooms);
        return 1;
    }

    public Room addRoom(Room room) { return roomRepository.save(room); }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        Iterable<Room> db_rooms = roomRepository.findAll();

        db_rooms.forEach(r -> {
            rooms.add(r);
        });
        return rooms;
    }

    public int deleteRoomById(UUID id) { roomRepository.deleteById(id); return 1; }

    // HOTEL ROOM SERVICE
    public HotelRoom addHotelRoom(HotelRoom room) {
        return hotelRoomRepository.save(room);
    }

    public List<HotelRoom> getAllHotelRooms() {
        List<HotelRoom> hotelRooms = new ArrayList<>();
        Iterable<HotelRoom> db_hotelRooms= hotelRoomRepository.findAll();

        db_hotelRooms.forEach(r -> {
            hotelRooms.add(r);
        });
        return hotelRooms;
    }

    public HotelRoom getHotelRoomById(UUID id) {
        return hotelRoomRepository.findById(id).get();
    }

    public int deleteHotelRoomById(UUID id) { hotelRoomRepository.deleteById(id); return 1; }

    public HotelRoom updateHotelRoomTypeById(UUID id, HotelRoomType newRoomType) {
        HotelRoom db_hotelRoom = getHotelRoomById(id);
        db_hotelRoom.setHotelRoomType(newRoomType);

        return hotelRoomRepository.save(db_hotelRoom);
    }
    public int deleteAllHotelRooms() { hotelRoomRepository.deleteAll(); return 1; }

    // MEETING ROOM SERVICE
    public MeetingRoom addMeetingRoom(MeetingRoom room) {
        return meetingRoomRepository.save(room);
    }


    public List<MeetingRoom> getAllMeetingRooms() {
        List<MeetingRoom> meetingRooms = new ArrayList<>();
        Iterable<MeetingRoom> db_meetingRooms= meetingRoomRepository.findAll();

        db_meetingRooms.forEach(r -> {
            meetingRooms.add(r);
        });
        return meetingRooms;
    }

    public MeetingRoom getMeetingRoomById(UUID id) {
        return meetingRoomRepository.findById(id).get();
    }

    public int deleteMeetingRoomById(UUID id) { meetingRoomRepository.deleteById(id); return 1; }

    public MeetingRoom updateMeetingRoomById(UUID id, MeetingRoomType newRoomType) {
        MeetingRoom db_meetingRoom = getMeetingRoomById(id);
        db_meetingRoom.setMeetingRoomType(newRoomType);

        return meetingRoomRepository.save(db_meetingRoom);
    }

    public int deleteAllMeetingRooms() { meetingRoomRepository.deleteAll(); return 1; }
}
