package com.humanCompilers.hotelTulip.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("name", "Laura");
        //model.addAttribute("name", "Laura");

        return modelAndView;
    }
}
