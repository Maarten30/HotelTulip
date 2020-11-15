package com.humanCompilers.hotelTulip.controller;

import com.humanCompilers.hotelTulip.model.User;
import com.humanCompilers.hotelTulip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login() {

        ModelAndView modelAndView = new ModelAndView("login");

        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView("logout");

        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("create_account");
        User newUser = new User();
        modelAndView.addObject("newUser", newUser); // key, value
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView createUser(@ModelAttribute User newUser) {

        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);
        System.out.println(newUser);

        userService.createUser(newUser);

        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }
}
