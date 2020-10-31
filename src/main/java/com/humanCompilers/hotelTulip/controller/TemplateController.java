package com.humanCompilers.hotelTulip.controller;


import com.humanCompilers.hotelTulip.model.Reservation;
import com.humanCompilers.hotelTulip.model.Room;
import com.humanCompilers.hotelTulip.model.RoomType;
import com.humanCompilers.hotelTulip.model.Tarifa;
import com.humanCompilers.hotelTulip.service.ReservationService;
import com.humanCompilers.hotelTulip.service.RoomService;
import com.humanCompilers.hotelTulip.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class TemplateController {

    private final ReservationService reservationService;
    private final RoomService roomService;
    private final TarifaService tarifaService;

    @Autowired
    public TemplateController(ReservationService reservationService, RoomService roomService,
                              TarifaService tarifaService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.tarifaService = tarifaService;
    }

    @GetMapping("/")
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView("Index");

        modelAndView.addObject("name", "Laura");
        //model.addAttribute("name", "Laura");

        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {

        ModelAndView modelAndView = new ModelAndView("LogIn");

        return modelAndView;
    }

    @GetMapping("/tarifas")
    public ModelAndView tarifas() {

        ModelAndView modelAndView = new ModelAndView("Price");

        List<Tarifa> lista_tarifas = tarifaService.getAllTarifas();

        lista_tarifas.stream().forEach((t) -> {
            System.out.println(t.getSeason().getSeason().toLowerCase() + "_" + t.getRoom_type().getRoomType().toLowerCase());

            modelAndView.addObject(
                    t.getSeason().getSeason().toLowerCase() +
                    "_" +
                    t.getRoom_type().getRoomType().toLowerCase()
                    , t);
        });

        return modelAndView;
    }

    @GetMapping("/contact")
    public ModelAndView contact() {

        ModelAndView modelAndView = new ModelAndView("Contact");

        return modelAndView;
    }

    @GetMapping("/reservations")
    public ModelAndView reservations() {

        ModelAndView modelAndView = new ModelAndView("Reservation");
        // ModelAndView modelAndView = new ModelAndView("reservationPostPrueba");
        // modelAndView.addObject("reservation", new Reservation());

        // Room cuarto_prueba = new Room(UUID.randomUUID(), RoomType.SINGLE);
        // roomService.addRoom(cuarto_prueba.getId(), cuarto_prueba);
        // modelAndView.addObject("room", cuarto_prueba);

        return modelAndView;
    }

    @PostMapping("/reservations")
    public ModelAndView createReservation(@RequestParam(value = "checkinDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkin,
                                          @RequestParam(value = "checkoutDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkout,
                                          @RequestParam(value = "people", required = true) Integer people) {
        System.out.println("La primera fecha recibida es: ");
        System.out.println(checkin);
        System.out.println("La segunda fecha recibida es: ");
        System.out.println(checkout);
        System.out.println("El int de people recibido es: " + people.toString());

        Room available_room = null;
        Reservation reservation = new Reservation();

        if(people == 1) {
            System.out.println("Entra en 1");
            available_room = reservationService.CheckAvailability(checkin, checkout, RoomType.SINGLE);
            System.out.println(available_room);
        } else if(people == 2) {
            System.out.println("Entra en 2");
            available_room = reservationService.CheckAvailability(checkin, checkout, RoomType.DOUBLE);
        } else {
            System.out.println("Entra en 3");
            available_room = reservationService.CheckAvailability(checkin, checkout, RoomType.TRIPLE);
        }

        if(available_room != null) {

            // Set checkin, checkout and room
            reservation.setCheckinDate(checkin);
            reservation.setCheckoutDate(checkout);
            reservation.setReservedRoom(available_room);

            // Calculate the reservation price
            double price = reservationService.calculateTotalPrice(reservation.getCheckinDate(),
                    reservation.getCheckoutDate(),
                    reservation.getReservedRoom());
            // Set the price
            reservation.setTotalPrice(price);
            // Set a UUID
            reservation.setId(UUID.randomUUID());
            // Save the reservation
            reservationService.addReservation(reservation);
        }

        // View with result
        ModelAndView modelAndView = new ModelAndView();

        if(available_room != null) {
            modelAndView.setViewName("reservationResult");
            modelAndView.addObject("reservation", reservation);
        } else {
            modelAndView.setViewName("reservationError");
            modelAndView.addObject("message", "Sorry! No room for the desired people was available");
        }

        return modelAndView;
    }


}
