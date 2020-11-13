package com.humanCompilers.hotelTulip.controller;


import com.humanCompilers.hotelTulip.model.*;
import com.humanCompilers.hotelTulip.service.ReservationService;
import com.humanCompilers.hotelTulip.service.RoomService;
import com.humanCompilers.hotelTulip.service.TarifaService;
import com.humanCompilers.hotelTulip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class MainController {

    private final ReservationService reservationService;
    private final RoomService roomService;
    private final TarifaService tarifaService;


    @Autowired
    public MainController(ReservationService reservationService, RoomService roomService,
                          TarifaService tarifaService, UserService userService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.tarifaService = tarifaService;
     }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/tarifas")
    public ModelAndView tarifas() {

        ModelAndView modelAndView = new ModelAndView("prices");

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
        return new ModelAndView("contact");
    }

    @GetMapping("/privacyPolicy")
    public ModelAndView cookies() {
        return new ModelAndView("cookies");
    }

}
