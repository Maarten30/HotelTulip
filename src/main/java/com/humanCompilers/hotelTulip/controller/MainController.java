package com.humanCompilers.hotelTulip.controller;


import com.humanCompilers.hotelTulip.model.*;
import com.humanCompilers.hotelTulip.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Clase que responde a las peticiones http generales de la aplicación
 * @author HumanCompilers
 */
@Controller
public class MainController {

    private final ReservationService reservationService;
    private final RoomService roomService;
    private final TarifaService tarifaService;
    private final TarifaMeetingRoomService tarifaMeetingRoomService;

    /**
     *
     * @param reservationService Instancia de la clase reservationService para poder hacerle llamadas
     * @param roomService Instancia de la clase roomService para poder hacerle llamadas
     * @param tarifaService Instancia de la clase tarifaService para poder hacerle llamadas
     * @param userService Instancia de la clase userService para poder hacerle llamadas
     * @param tarifaMeetingRoomService Instancia de la clase tarifaMeetingRoomService para poder hacerle llamadas
     */
    @Autowired
    public MainController(ReservationService reservationService, RoomService roomService,
                          TarifaService tarifaService, UserService userService,
                          TarifaMeetingRoomService tarifaMeetingRoomService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.tarifaService = tarifaService;
        this.tarifaMeetingRoomService = tarifaMeetingRoomService;
     }

    /**
     * Método al que se le llama al realizar una petición Get con la url de /
     * @return Devuelve la vista de la página principal de la aplicación
     */
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }

    /**
     * Método al que se le llama al realizar una petición Get con la url de /tarifas
     * @return Devuelve la vista de las tarifas existentes en el hotel
     */
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

    /**
     * Método al que se le llama al realizar una petición Get con la url de /contact
     * @return Devuelve la vista del contacto con el hotel
     */
    @GetMapping("/contact")
    public ModelAndView contact() {
        return new ModelAndView("contact");
    }

    /**
     * Método al que se le llama al realizar una petición Get con la url de /cookies
     * @return Devuelve la vista de las políticas de privacidad del hotel
     */
    @GetMapping("/privacyPolicy")
    public ModelAndView cookies() {
        return new ModelAndView("cookies");
    }

    /**
     * Atributo general para saber quién es el usuario logeado
     * @return devuelve el usuario logeado en la aplicación en ese momento
     */
    @ModelAttribute("loggedinUser")
    public User globalUserObject(Model model) {

        String username = "";
        String firstname = "";
        boolean activeUser = false;

        // Comprueba si el usuario esta logeado
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            if(!(SecurityContextHolder.getContext().getAuthentication()
                    instanceof AnonymousAuthenticationToken) ){
                // Si entra aqui significa que hay un usario logeado (Descartando el usuario por defecto 'anonymous')
                activeUser = true;
            }
        }

        // Coge el usuario logeado
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(user instanceof UserDetails){
            username = ((UserDetails)user).getUsername(); // Si no hay usuario logeado será anonymous
            if(activeUser)
                firstname = ((User)user).getFirstName(); // El if es para comprobar que el usuario no sea anonymous
        }

        User active_user = new User();

        // Create User pojo class
        active_user.setUsername(username);
        active_user.setFirstName(firstname);

        model.addAttribute("userActive", activeUser);

        // Este objeto estara disponible en todos los htmls con el nombre 'loggedinUser' definido arriba en @ModelAttribute
        return active_user;
    }

}
