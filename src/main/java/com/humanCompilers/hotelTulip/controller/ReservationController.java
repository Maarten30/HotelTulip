package com.humanCompilers.hotelTulip.controller;

import com.humanCompilers.hotelTulip.model.*;
import com.humanCompilers.hotelTulip.service.ReservationService;
import com.humanCompilers.hotelTulip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Clase que responde a las peticiones http relativas a las reservas
 * @author HumanCompilers
 */
@Controller
@RequestMapping("reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;

    /**
     * Constructor de la clase
     * @param reservationService Instancia de la clase reservationService para poder hacerle llamadas
     * @param userService Instancia de la clase userService para poder hacerle llamadas
     */
    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    /**
     * Método al que se le llama al realizar una petición Get con la url de /newReservation
     * @return Devuelve la vista para poder realizar una reserva
     */
    @GetMapping("/newReservation")
    public ModelAndView reservations() {
        return new ModelAndView("reservation");
    }

    /**
     * Método al que se le llama al realizar una petición Post con la url de /newReservation
     * @param checkin Fecha de inicio de la reserva
     * @param checkout Fecha de finalización de la reserva
     * @param people Número de personas dentro de la reserva
     * @return En el caso de haber disponibilidad para las fechas sugeridas, el método devolverá la vista con los detalles
     * de la reserva. Sin embargo, en caso de no haber disponibilidad devolverá la misma vista de realizar reserva
     * pero con una alerta.
     */
    @PostMapping("/newReservation")
    public ModelAndView createReservation(@RequestParam(value = "checkinDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkin,
                                          @RequestParam(value = "checkoutDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkout,
                                          @RequestParam(value = "people", required = true) Integer people) {

        boolean roomsAvailable = true;
        boolean datesAreValid = true;

        HotelRoom available_room = null;
        Reservation reservation = new Reservation();

        if(people == 1) {
            available_room = reservationService.CheckHotelRoomAvailability(checkin, checkout, HotelRoomType.SINGLE);
            System.out.println(available_room);
        } else if(people == 2) {
            available_room = reservationService.CheckHotelRoomAvailability(checkin, checkout, HotelRoomType.DOUBLE);
        } else {
            available_room = reservationService.CheckHotelRoomAvailability(checkin, checkout, HotelRoomType.TRIPLE);
        }

        if(available_room != null) {

            // Set checkin, checkout and room
            reservation.setCheckinDate(checkin);
            reservation.setCheckoutDate(checkout);
            reservation.setReservedRoom(available_room);

            // Calculate the reservation price
            Double price = reservationService.calculateTotalPrice(
                    reservation.getCheckinDate(),
                    reservation.getCheckoutDate(),
                    (HotelRoom)reservation.getReservedRoom()
            );
            if(price != null) {
                // Set the price
                reservation.setTotalPrice(price);
                //Set a User
                Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User usuario = null;

                if(user instanceof UserDetails){
                  String username = ((UserDetails)user).getUsername(); // Si no hay usuario logeado será anonymous
                    usuario = (User) userService.loadUserByUsername(username);
                }
                reservation.setUser(usuario);
                // Save the reservation
                reservationService.addReservation(reservation);
            } else {
                datesAreValid = false;
            }
        } else {
            roomsAvailable = false;
        }

        // View with result
        ModelAndView modelAndView = new ModelAndView();

        if(roomsAvailable && datesAreValid) {
            System.out.println("Available room = null");
            modelAndView.setViewName("reservation_result");
            modelAndView.addObject("reservation", reservation);
        } else {
            if(!roomsAvailable) {
                System.out.println("No disponible");
                modelAndView.setViewName("reservation");
                modelAndView.addObject("message", "Sorry! No room for the desired people was available");
            } else if(!datesAreValid) {
                modelAndView.setViewName("reservation");
                modelAndView.addObject("message", "Sorry! Dates out of scope");
            }

        }

        return modelAndView;
    }

    /**
     * Método al que se le llama al realizar una petición Post con la url de /newMeetingRoomReservation
     * @param checkin Fecha de inicio de la reserva
     * @param checkout Fecha de finalización de la reserva
     * @param people Número de personas dentro de la reserva
     * @return En el caso de haber disponibilidad para las fechas sugeridas, el método devolverá la vista con los detalles
     * de la reserva. Sin embargo, en caso de no haber disponibilidad devolverá la misma vista de realizar reserva
     * pero con una alerta.
     */
    @PostMapping("/newMeetingRoomReservation")
    public ModelAndView createMeetingRoomReservation(@RequestParam(value = "checkinDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkin,
                                          @RequestParam(value = "checkoutDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkout,
                                          @RequestParam(value = "sala", required = true) Integer people) {

        boolean roomsAvailable = true;
        boolean datesAreValid = true;

        MeetingRoom available_room = null;
        Reservation reservation = new Reservation();

        if(people == 10) {
            available_room = reservationService.CheckMeetingRoomAvailability(checkin, checkout, MeetingRoomType.SMALL);
            System.out.println(available_room);
        } else if(people == 20) {
            available_room = reservationService.CheckMeetingRoomAvailability(checkin, checkout, MeetingRoomType.MEDIUM);
        } else {
            available_room = reservationService.CheckMeetingRoomAvailability(checkin, checkout, MeetingRoomType.LARGE);
        }

        if(available_room != null) {


            // Set checkin, checkout and room
            reservation.setCheckinDate(checkin);
            reservation.setCheckoutDate(checkout);
            reservation.setReservedRoom(available_room);


            // Calculate the reservation price
            Double price = reservationService.calculateMeetingRoomTotalPrice(
                    reservation.getCheckinDate(),
                    reservation.getCheckoutDate(),
                    (MeetingRoom)reservation.getReservedRoom()
            );

            if(price != null) {
                // Set the price
                reservation.setTotalPrice(price);
                //Set a User
                Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User usuario = null;

                if(user instanceof UserDetails){
                    String username = ((UserDetails)user).getUsername(); // Si no hay usuario logeado será anonymous
                    usuario = (User) userService.loadUserByUsername(username);
                }
                reservation.setUser(usuario);
                // Save the reservation
                reservationService.addReservation(reservation);
            } else {
                datesAreValid = false;
            }
        } else {
            roomsAvailable = false;
        }

        // View with result
        ModelAndView modelAndView = new ModelAndView();

        if(roomsAvailable && datesAreValid) {
            System.out.println("Available room = null");
            modelAndView.setViewName("reservation_result");
            modelAndView.addObject("reservation", reservation);
        } else {
            if(!roomsAvailable) {
                System.out.println("No disponible");
                modelAndView.setViewName("reservation");
                modelAndView.addObject("message", "Sorry! No room for the desired people was available");
            } else if(!datesAreValid) {
                modelAndView.setViewName("reservation");
                modelAndView.addObject("message", "Sorry! Dates out of scope");
            }

        }

        return modelAndView;
    }

    /**
     * Método al que se le llama al realizar una petición Get con la url de /confirmation
     * @return Devuelve la vista con la confirmación de la reserva y los detalles de esta
     */
    @GetMapping("/confirmation")
    public ModelAndView confirmation() {
        return new ModelAndView("reservation_result");
    }

    /**
     * Método al que se le llama al realizar una petición Get con la url de /myReservations
     * @return Devuelve la vista con las reservas pertenecientes al usuario logeado.
     */
    @GetMapping("/myReservations")
    public ModelAndView userBookings() {

        ModelAndView modelAndView = new ModelAndView("user_reservations");


        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if(user instanceof UserDetails){
            username = ((UserDetails)user).getUsername();
        } else {
            username = user.toString();
        }

        System.out.println(username);

        User usuario = new User();
        usuario.setUsername(username);
        List<Reservation> reservations = reservationService.getReservationsByUser(usuario);

        modelAndView.addObject("Reservas", reservations);

        return modelAndView;
    }

    /**
     * Método al que se le llama al realizar una petición Post con la url de /userBookings/{id}
     * @param id código identificativo de la reserva a eliminar
     * @return Devuelve la vista con las reservar del usuario logeado sin la recién eliminada
     */
    @PostMapping("/userBookings/{id}")
    public ModelAndView deleteBookings(@PathVariable UUID id){

        reservationService.deleteReservationById(id);

        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if(user instanceof UserDetails){
            username = ((UserDetails)user).getUsername();
        } else {
            username = user.toString();
        }

        User usuario = new User();
        usuario.setUsername(username);
        List<Reservation> reservations = reservationService.getReservationsByUser(usuario);

        ModelAndView modelAndView = new ModelAndView("user_reservations");
        modelAndView.addObject("Reservas", reservations);

        return modelAndView;
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
