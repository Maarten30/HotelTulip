package com.humanCompilers.hotelTulip.controller;

import com.humanCompilers.hotelTulip.model.*;

import com.humanCompilers.hotelTulip.service.ReservationService;
import com.humanCompilers.hotelTulip.service.RoomService;
import com.humanCompilers.hotelTulip.service.TarifaService;
import com.humanCompilers.hotelTulip.service.UserService;
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
    private final RoomService roomService;
    private final UserService userService;

    @Autowired
    public ApiController(ReservationService reservationService, TarifaService tarifaService,
                         RoomService roomService, UserService userService) {
        this.reservationService = reservationService;
        this.tarifaService = tarifaService;
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

    @GetMapping("/getReservation")
    public Reservation getReservation(@RequestParam("id") UUID id) {
        System.out.println(id);
        return reservationService.getReservationById(id);
    }


    // TARIFAS
    @GetMapping("/tarifas")
    public List<Tarifa> getTarifas() { return tarifaService.getAllTarifas(); }

    @PostMapping("/insertTarifa")
    public Tarifa insertTarifa(@RequestBody Tarifa tarifa){ return tarifaService.addTarifa(tarifa); }


    // ROOMS
    @GetMapping("/rooms")
    public List<Room> getRooms() { return roomService.getAllRooms(); }

    @PostMapping("/insertHotelRoom")
    public HotelRoom insertHotelRoom(@RequestBody HotelRoom room) { return roomService.addHotelRoom(room); }

    @PostMapping("/insertMeetingRoom")
    public MeetingRoom insertMeetingRoom(@RequestBody MeetingRoom room) { return roomService.addMeetingRoom(room); }


    // USERS
    @GetMapping("/users")
    public List<User> getUsers() { return userService.getAllUsers(); }

    @PostMapping("/insertUser")
    public User insertUser(@RequestBody User user) { return userService.createUser(user); }

}