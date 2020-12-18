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

/**
 * Clase que responde a las peticiones http relativas al usuario
 * @author HumanCompilers
 */
@Controller
@RequestMapping("user")
public class AuthController {

    private final UserService userService;

    /**
     * Constructor de la clase
     * @param userService Instancia de la clase userService para poder hacerle llamadas
     */
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Método al que se le llama al realizar una petición Get con la url de /login
     * @return Devuelve la vista correspondiente al inicio de sesión
     */
    @GetMapping("/login")
    public ModelAndView login() {

        ModelAndView modelAndView = new ModelAndView("login");

        return modelAndView;
    }

    /**
     * Método al que se le llama al realizar una petición Get con la url de /logout
     * @return Devuelve la vista correspondiente a cerrar sesión
     */
    @GetMapping("/logout")
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView("logout");

        return modelAndView;
    }

    /**
     * Método al que se le llama al realizar una petición Get con la url de /registration
     * @return Devuelve la vista correspondiente a la creación de cuenta
     */
    @GetMapping("/registration")
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("create_account");
        User newUser = new User();
        modelAndView.addObject("newUser", newUser); // key, value
        return modelAndView;
    }

    /**
     * Método al que se le llama al realizar una petición Post con la url de /registration
     * @param newUser Nuevo usuario creado en la aplicación
     * @return Si el email introducido por el usuario no existe en la aplicación, se devolverá la vista de inicio de
     * sesión, sin embargo, en caso de repetir el email, aparecerá una alerta por pantalla con dicha indicación
     */
    @PostMapping("/registration")
    public ModelAndView createUser(@ModelAttribute User newUser) {

        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);
        System.out.println(newUser);

        User prueba = userService.createUser(newUser);

        ModelAndView modelAndView = new ModelAndView();

        if(prueba != null)
        {
            System.out.println(prueba);
            modelAndView.setViewName("login");
            System.out.println(newUser.getUsername());
        } else{
            System.out.println("soy null");
            modelAndView.setViewName("create_account");
            User newUser1 = new User();
            modelAndView.addObject("newUser", newUser1);
            modelAndView.addObject("registrationMessage", "Ya existe una cuenta con el email introducido, " +
                    "por favor, intentelo con otro");
        }

        return modelAndView;
    }
}
