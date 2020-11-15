package com.humanCompilers.hotelTulip.controller;


import com.humanCompilers.hotelTulip.model.*;
import com.humanCompilers.hotelTulip.service.*;
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
    private final TarifaMeetingRoomService tarifaMeetingRoomService;


    @Autowired
    public MainController(ReservationService reservationService, RoomService roomService,
                          TarifaService tarifaService, UserService userService,
                          TarifaMeetingRoomService tarifaMeetingRoomService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.tarifaService = tarifaService;
        this.tarifaMeetingRoomService = tarifaMeetingRoomService;
     }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/tarifas")
    public ModelAndView tarifas() {

        ModelAndView modelAndView = new ModelAndView("prices");

        List<Tarifa> lista_tarifas = tarifaService.getAllTarifas();
        List<TarifaMeetingRoom> lista_tarifas_meeting = tarifaMeetingRoomService.getAllTarifasMeetingRoom();

        lista_tarifas.stream().forEach((t) -> {
            System.out.println(t.getSeason().getSeason().toLowerCase() + "_" + t.getRoom_type().getRoomType().toLowerCase());

            modelAndView.addObject(
                    t.getSeason().getSeason().toLowerCase() +
                    "_" +
                    t.getRoom_type().getRoomType().toLowerCase()
                    , t);
        });

        lista_tarifas_meeting.stream().forEach((p) -> {
            System.out.println(p.getSeason().getSeason().toLowerCase() + "_" + p.getRoom_type().getMeetingRoomType().toLowerCase());

            modelAndView.addObject(
                    p.getSeason().getSeason().toLowerCase() +
                            "_" +
                            p.getRoom_type().getMeetingRoomType().toLowerCase()
                    , p);
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
