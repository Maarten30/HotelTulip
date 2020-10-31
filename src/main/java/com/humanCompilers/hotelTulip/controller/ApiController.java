package com.humanCompilers.hotelTulip.controller;

import com.humanCompilers.hotelTulip.model.Reservation;

import com.humanCompilers.hotelTulip.model.Room;
import com.humanCompilers.hotelTulip.model.Tarifa;
import com.humanCompilers.hotelTulip.service.ReservationService;
import com.humanCompilers.hotelTulip.service.RoomService;
import com.humanCompilers.hotelTulip.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("api")
@RestController
public class ApiController {

    private final ReservationService reservationService;
    private final TarifaService tarifaService;
    private final RoomService roomService;

    @Autowired
    public ApiController(ReservationService reservationService, TarifaService tarifaService,
                         RoomService roomService) {
        this.reservationService = reservationService;
        this.tarifaService = tarifaService;
        this.roomService = roomService;
    }

    @GetMapping("/reservas")
    public List<Reservation> getReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/tarifas")
    public List<Tarifa> getTarifas() { return tarifaService.getAllTarifas(); }

    @GetMapping("/rooms")
    public List<Room> getRooms() { return roomService.getAllRooms(); }

}