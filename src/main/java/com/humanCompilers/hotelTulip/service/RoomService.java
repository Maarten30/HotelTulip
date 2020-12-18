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

/**
 * Clase que proporciona la lógica de negocio relacionada con las habitaciones de hotel
 * @HumanCompilers
 */
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRoomRepository hotelRoomRepository;
    private final MeetingRoomRepository meetingRoomRepository;

    /**
     * Constructor de la clase
     * @param roomRepository Instancia de la clase roomService para poder hacerle llamadas
     * @param hotelRoomRepository Instancia de la clase hotelRoomRepository para poder hacerle llamadas
     * @param meetingRoomRepository Instancia de la clase meetingRoomRepository para poder hacerle llamadas
     */
    @Autowired
    public RoomService(RoomRepository roomRepository,
                       HotelRoomRepository hotelRoomRepository,
                       MeetingRoomRepository meetingRoomRepository) {
        this.roomRepository = roomRepository;
        this.hotelRoomRepository = hotelRoomRepository;
        this.meetingRoomRepository = meetingRoomRepository;
    }

    // PARENT ROOM SERVICE

    /**
     * Método que sirve para guardar una lista de habitaciones y salas en la base de datos
     * @param rooms lista de habitaciones a guardar
     */
    public int addRooms(List<Room> rooms) {
        roomRepository.saveAll(rooms);
        return 1;
    }

    /**
     * Método para guardar una habitación o sala en la base de datos
     * @param room habitación a guardar en la base de datos
     */
    public Room addRoom(Room room) { return roomRepository.save(room); }

    /**
     * Método que sirve para obtener todas las habitaciones y salas de la base de datos
     * @return devuelve una lista con todas las habitaciones y salas existentes en la base de datos
     */
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        Iterable<Room> db_rooms = roomRepository.findAll();

        db_rooms.forEach(r -> {
            rooms.add(r);
        });
        return rooms;
    }

    /**
     * Método que sirve para eliminar una habitación o sola por id
     * @param id identificador de la habitación o sala a eliminar
     */
    public int deleteRoomById(UUID id) { roomRepository.deleteById(id); return 1; }

    // HOTEL ROOM SERVICE

    /**
     * Método que sirve para añadir una habitación de hotel a la base de datos
     * @param room habitación a añadir a la base de datos
     */
    public HotelRoom addHotelRoom(HotelRoom room) {
        return hotelRoomRepository.save(room);
    }

    /**
     * Método para obtener todas las habitaciones de hotel de la base de datos
     * @return devuelve una lista con todas las habitaciones de hotel existentes
     */
    public List<HotelRoom> getAllHotelRooms() {
        List<HotelRoom> hotelRooms = new ArrayList<>();
        Iterable<HotelRoom> db_hotelRooms= hotelRoomRepository.findAll();

        db_hotelRooms.forEach(r -> {
            hotelRooms.add(r);
        });
        return hotelRooms;
    }

    /**
     * Método que sirve para obtener una habitación de hotel por id
     * @param id identificador de la habitación a obtener
     */
    public HotelRoom getHotelRoomById(UUID id) {
        return hotelRoomRepository.findById(id).get();
    }

    /**
     * Método que sirve para eliminar una habitación por id
     * @param id identificador de la habitación a eliminar
     */
    public int deleteHotelRoomById(UUID id) { hotelRoomRepository.deleteById(id); return 1; }

    /**
     * Método para actualizar una habitación por id
     * @param id identificador de la habitación a modificar
     * @param newRoomType Nueva habitación a insertar en la base de datos
     */
    public HotelRoom updateHotelRoomTypeById(UUID id, HotelRoomType newRoomType) {
        HotelRoom db_hotelRoom = getHotelRoomById(id);
        db_hotelRoom.setHotelRoomType(newRoomType);

        return hotelRoomRepository.save(db_hotelRoom);
    }

    /**
     * Método para eliminar todas las habitaciones de hotel existentes de la base de datos
     */
    public int deleteAllHotelRooms() { hotelRoomRepository.deleteAll(); return 1; }

    // MEETING ROOM SERVICE

    /**
     * Método que sirve para añadir una sala a la base de datos
     * @param room sala a añadir
     */
    public MeetingRoom addMeetingRoom(MeetingRoom room) {
        return meetingRoomRepository.save(room);
    }

    /**
     * Método que sirve para obtener todas las salas existentes de la base de datos
     * @return lista con todas las salas existentes
     */
    public List<MeetingRoom> getAllMeetingRooms() {
        List<MeetingRoom> meetingRooms = new ArrayList<>();
        Iterable<MeetingRoom> db_meetingRooms= meetingRoomRepository.findAll();

        db_meetingRooms.forEach(r -> {
            meetingRooms.add(r);
        });
        return meetingRooms;
    }

    /**
     * Método que sirve para obtener una sala por id
     * @param id identificador de la sala a obtener
     */
    public MeetingRoom getMeetingRoomById(UUID id) {
        return meetingRoomRepository.findById(id).get();
    }

    /**
     * Método que se utiliza para eliminar una sala por id
     * @param id identificador de la sala a eliminar
     */
    public int deleteMeetingRoomById(UUID id) { meetingRoomRepository.deleteById(id); return 1; }

    /**
     * Método que sirve para actualizar una sala de la base de datos
     * @param id identificador de la sala a modificar
     * @param newRoomType Nueva sala a insertar en la base de datos
     */
    public MeetingRoom updateMeetingRoomById(UUID id, MeetingRoomType newRoomType) {
        MeetingRoom db_meetingRoom = getMeetingRoomById(id);
        db_meetingRoom.setMeetingRoomType(newRoomType);

        return meetingRoomRepository.save(db_meetingRoom);
    }

    /**
     * Método que sirve para eliminar todas las salas existentes en la base de datos
     */
    public int deleteAllMeetingRooms() { meetingRoomRepository.deleteAll(); return 1; }
}
