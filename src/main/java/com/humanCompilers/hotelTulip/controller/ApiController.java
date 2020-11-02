package com.humanCompilers.hotelTulip.controller;

import com.humanCompilers.hotelTulip.model.Reservation;

import com.humanCompilers.hotelTulip.model.Room;
import com.humanCompilers.hotelTulip.model.Tarifa;
import com.humanCompilers.hotelTulip.model.User;
import com.humanCompilers.hotelTulip.service.ReservationService;
import com.humanCompilers.hotelTulip.service.RoomService;
import com.humanCompilers.hotelTulip.service.TarifaService;
import com.humanCompilers.hotelTulip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/reservas")
    public List<Reservation> getReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/tarifas")
    public List<Tarifa> getTarifas() { return tarifaService.getAllTarifas(); }

    @GetMapping("/rooms")
    public List<Room> getRooms() { return roomService.getAllRooms(); }

    /**
     * En realidad este metodo no va a existir aqui porque desde la pagina web no se pueden ver todos los usuarios.
     * Solo esta para probar con postman que añade bien.S
     */
    @GetMapping("/users")
    public List<User> getUsers() { return userService.getUsers(); }

    @PostMapping("/registro")
    public int insertUser(@RequestParam(value = "username", required = true) String username,
                           @RequestParam(value = "email", required = true) String email,
                           @RequestParam(value = "password", required = true) String password)
    {
        User newUser = new User(username, email, password);
        userService.addUser(newUser);
        return 1;
    }
}