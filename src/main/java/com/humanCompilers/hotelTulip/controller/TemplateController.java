package com.humanCompilers.hotelTulip.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TemplateController {

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

        return modelAndView;
    }


}
