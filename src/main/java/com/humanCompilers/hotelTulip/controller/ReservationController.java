package com.humanCompilers.hotelTulip.controller;

import com.humanCompilers.hotelTulip.model.Reservation;

import com.humanCompilers.hotelTulip.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservations")
    public ModelAndView createReservation(@RequestBody Reservation reservation ) {

        reservationService.addReservation(reservation);

        ModelAndView modelAndView = new ModelAndView("Index");
        modelAndView.addObject("name", "Laura");

        return modelAndView;
    }
}