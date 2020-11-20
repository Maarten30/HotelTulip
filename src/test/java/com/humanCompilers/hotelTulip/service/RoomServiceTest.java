package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.HotelRoomRepository;
import com.humanCompilers.hotelTulip.dao.MeetingRoomRepository;
import com.humanCompilers.hotelTulip.dao.RoomRepository;
import com.humanCompilers.hotelTulip.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class RoomServiceTest {

    static RoomService roomService;

    static RoomRepository roomRepository = mock((RoomRepository.class));
    static HotelRoomRepository hotelRoomRepository = mock((HotelRoomRepository.class));
    static MeetingRoomRepository meetingRoomRepository = mock((MeetingRoomRepository.class));

    static HotelRoom hotelRoom_1;
    static HotelRoom hotelRoom_2;

    static MeetingRoom meetingRoom_1;
    static MeetingRoom meetingRoom_2;

    @BeforeAll
    public static void init() {
        roomService = new RoomService(roomRepository, hotelRoomRepository, meetingRoomRepository);

        hotelRoom_1 = new HotelRoom();
        hotelRoom_1.setHotelRoomType(HotelRoomType.SINGLE);

        hotelRoom_2 = new HotelRoom();
        hotelRoom_2.setHotelRoomType(HotelRoomType.DOUBLE);

        meetingRoom_1= new MeetingRoom();
        meetingRoom_1.setMeetingRoomType(MeetingRoomType.LARGE);

        meetingRoom_2= new MeetingRoom();
        meetingRoom_2.setMeetingRoomType(MeetingRoomType.SMALL);

    }


    @Test
    void addHotelRoom() {
        when(hotelRoomRepository.save(any())).thenReturn(hotelRoom_1);

        HotelRoom hotelRoom = roomService.addHotelRoom(hotelRoom_1);
        HotelRoomType hotelRoomType = hotelRoom.getHotelRoomType();

        assertEquals(hotelRoom_1.getHotelRoomType(), hotelRoomType);
    }

    @Test
    void getAllHotelRooms() {
        when(hotelRoomRepository.findAll()).thenReturn(Stream.of(
                hotelRoom_1, hotelRoom_2
        ).collect(Collectors.toList()));

        assertEquals(2, roomService.getAllHotelRooms().size());
    }

    @Test
    void addMeetingRoom() {
        when(meetingRoomRepository.save(any())).thenReturn(meetingRoom_1);

        MeetingRoom meetingRoom = roomService.addMeetingRoom(meetingRoom_1);
        MeetingRoomType meetingRoomType = meetingRoom.getMeetingRoomType();

        assertEquals(meetingRoom_1.getMeetingRoomType(), meetingRoomType);
    }

    @Test
    void getAllMeetingRooms() {
        when(meetingRoomRepository.findAll()).thenReturn(Stream.of(
                meetingRoom_1, meetingRoom_2
        ).collect(Collectors.toList()));

        assertEquals(2, roomService.getAllMeetingRooms().size());
    }

    @Test
    void getAllRooms() {
        when(roomRepository.findAll()).thenReturn(Stream.of(
                meetingRoom_1, meetingRoom_2, hotelRoom_1, hotelRoom_2
        ).collect(Collectors.toList()));

        assertEquals(4, roomService.getAllRooms().size());
    }
}