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

@Controller
@RequestMapping("reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;

    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @GetMapping("/newReservation")
    public ModelAndView reservations() {
        return new ModelAndView("reservation");
    }

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



    @GetMapping("/confirmation")
    public ModelAndView confirmation() {
        return new ModelAndView("reservation_result");
    }

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

    @PostMapping("/userBookings/{id}")
    public ModelAndView deleteBookings(@PathVariable UUID id){

        System.out.println("El Id recibido es: ");
        System.out.println(id);

        reservationService.deleteReservationById(id);

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

        ModelAndView modelAndView = new ModelAndView("user_reservations");
        modelAndView.addObject("Reservas", reservations);

        return modelAndView;
    }

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
