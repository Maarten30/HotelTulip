package com.humanCompilers.hotelTulip.controller;

import com.humanCompilers.hotelTulip.model.*;

import com.humanCompilers.hotelTulip.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RequestMapping("api")
@RestController
public class ApiController {

    private final ReservationService reservationService;
    private final TarifaService tarifaService;
    private final TarifaMeetingRoomService tarifaMeetingRoomService;
    private final RoomService roomService;
    private final UserService userService;

    @Autowired
    public ApiController(ReservationService reservationService, TarifaService tarifaService,
                         TarifaMeetingRoomService tarifaMeetingRoomService,
                         RoomService roomService, UserService userService) {
        this.reservationService = reservationService;
        this.tarifaService = tarifaService;
        this.tarifaMeetingRoomService = tarifaMeetingRoomService;
        this.roomService = roomService;
        this.userService = userService;
    }


    // RESERVATIONS
    @GetMapping("/reservas")
    public List<Reservation> getReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping("/insertReservation")
    public Reservation insertReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    @DeleteMapping("/deleteReservation")
    public String deleteReservationById(@RequestParam("id") UUID id) {
        System.out.println(id);
        reservationService.deleteReservationById(id);
        return "Se ha borrado la reserva escogida";
    }

    @DeleteMapping("/deleteAllReservations")
    public String deleteAllreservations() {
        reservationService.deleteAllReservations();
        return "Se han borrado todas las reservas";
    }


    @GetMapping("/getReservation")
    public Reservation getReservation(@RequestParam("id") UUID id) {
        System.out.println(id);
        return reservationService.getReservationById(id);
    }


    // TARIFAS HABITACIONES
    @GetMapping("/tarifas")
    public List<Tarifa> getTarifas() { return tarifaService.getAllTarifas(); }

    @PostMapping("/insertTarifa")
    public Tarifa insertTarifa(@RequestBody Tarifa tarifa){ return tarifaService.addTarifa(tarifa); }

    @DeleteMapping("/deleteTarifa")
    public String deleteTarifaById(@RequestParam("id") Integer id) {
        System.out.println(id);
        tarifaService.deleteTarifa(id);
        return "Se ha borrado la tarifa escogida";
    }

    @DeleteMapping("/deleteAllTarifas")
    public String deleteAllTarifas() {
        tarifaService.deleteAllTarifas();
        return "Se han borrado todas las tarifa escogida";
    }

    // TARIFAS SALAS
    @GetMapping("/tarifas_meeting_room")
    public List<TarifaMeetingRoom> getTarifasMeetingRoom() { return tarifaMeetingRoomService.getAllTarifasMeetingRoom();}

    @PostMapping("/insertTarifa_meeting_room")
    public TarifaMeetingRoom insertTarifaMeetingRoom(@RequestBody TarifaMeetingRoom tarifa){
        return tarifaMeetingRoomService.addTarifaMeetingRoom(tarifa); }

    @DeleteMapping("/deleteTarifa_meeting_room")
    public String deleteTarifaMeetingRoomById(@RequestParam("id") Integer id) {
        System.out.println(id);
        tarifaMeetingRoomService.deleteTarifaMeetingRoomById(id);
        return "Se ha borrado la tarifa escogida";
    }

    @DeleteMapping("/deleteAllTarifas_meeting_room")
    public String deleteAllTarifaMeetingRoom() {
        tarifaMeetingRoomService.deleteAllTarifasMeetingRoom();
        return "Se han borrado todas las tarifa escogida";
    }


    // ROOMS
    @GetMapping("/rooms")
    public List<Room> getRooms() { return roomService.getAllRooms(); }

    @DeleteMapping("/deleteRooms")
    public String deleteRoomById(@RequestParam("id") UUID id) {
        System.out.println(id);
        roomService.deleteRoomById(id);
        return "Se ha borrado la habitación";
    }

    @GetMapping("/hotelRooms")
    public List<HotelRoom> getHotelRooms() { return roomService.getAllHotelRooms(); }

    @GetMapping("/meetingRooms")
    public List<MeetingRoom> getMeetingRooms() { return roomService.getAllMeetingRooms();}

    @PostMapping("/insertHotelRoom")
    public HotelRoom insertHotelRoom(@RequestBody HotelRoom room) { return roomService.addHotelRoom(room); }

    @PostMapping("/insertMeetingRoom")
    public MeetingRoom insertMeetingRoom(@RequestBody MeetingRoom room) { return roomService.addMeetingRoom(room); }

    @DeleteMapping("/deleteHotelRoomById")
    public String deleteHotelRoomById(@RequestParam("id") UUID id) {
        System.out.println(id);
        roomService.deleteHotelRoomById(id);
        return "Se ha borrado la habitación de hotel escogida";
    }

    @DeleteMapping("/deleteMeetingRoomById")
    public String deleteMeetingRoomById(@RequestParam("id") UUID id) {
        System.out.println(id);
        roomService.deleteMeetingRoomById(id);
        return "Se ha borrado la sala de reuniones escogida";
    }

    @DeleteMapping("/deleteAll_hotel_room")
    public String deleteAllHotelRoom() {
        roomService.deleteAllHotelRooms();
        return "Se han borrado todas las habitaciones de hotel";
    }

    @DeleteMapping("/deleteAll_Meeting_room")
    public String deleteAllMeetingRoom() {
        roomService.deleteAllMeetingRooms();
        return "Se han borrado todas las salas de reuniones";
    }


    // USERS
    @GetMapping("/users")
    public List<User> getUsers() { return userService.getAllUsers(); }

    @PostMapping("/insertUser")
    public User insertUser(@RequestBody User user) { return userService.createUser(user); }

}