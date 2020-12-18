package com.humanCompilers.hotelTulip.controller;

import com.humanCompilers.hotelTulip.model.*;

import com.humanCompilers.hotelTulip.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RequestMapping("api")
@RestController
/**
 * Clase que contiene el controlador de la aplicación y que devuelve la información en formato json
 * @author HumanCompilers
 */
public class ApiController {

    private final ReservationService reservationService;
    private final TarifaService tarifaService;
    private final TarifaMeetingRoomService tarifaMeetingRoomService;
    private final RoomService roomService;
    private final UserService userService;

    /**
     * Constructor de la clase
     * @param reservationService Instancia de la clase reservationService para poder hacerle llamadas
     * @param tarifaService Instancia de la clase tarifaService para poder hacerle llamadas
     * @param tarifaMeetingRoomService Instancia de la clase tarifaMeetingRoomService para poder hacerle llamadas
     * @param roomService Instancia de la clase roomService para poder hacerle llamadas
     * @param userService Instancia de la clase userService para poder hacerle llamadas
     */
    @Autowired
    public ApiController(ReservationService reservationService, TarifaService tarifaService,
                         TarifaMeetingRoomService tarifaMeetingRoomService,
                         RoomService roomService, UserService userService) {
        this.reservationService = reservationService;
        this.tarifaService = tarifaService;
        this.tarifaMeetingRoomService = tarifaMeetingRoomService;
        this.roomService = roomService;
        this.userService = userService;
    }


    // RESERVATIONS

    /**
     * Método que sirve para obtener todas las reservas existentes de la aplicación
     * @return Devuelve todas las reservar que existen en la aplicación
     */
    @GetMapping("/reservas")
    public List<Reservation> getReservations() {
        return reservationService.getAllReservations();
    }

    /**
     * Método que sirve para añadir una reserva a la aplicación
     * @param reservation Reserva que se desea añadir a la aplicación
     * @return Devuelve la reserva que se acaba de guardar para verificar que se ha guardado correctamente
     */
    @PostMapping("/insertReservation")
    public Reservation insertReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    /**
     * Método que sirve para eliminar una seserva de la aplicación
     * @param id de la reserva que se desea eliminar de la aplicación
     * @return Devuelve un comentario avisando que la reserva ha sido eliminado correctamente
     */
    @DeleteMapping("/deleteReservation")
    public String deleteReservationById(@RequestParam("id") UUID id) {
        reservationService.deleteReservationById(id);
        return "Se ha borrado la reserva escogida";
    }

    /**
     * Método para eliminar todas las reservas existentes en la aplicación
     * @return Devuelve un comentario avisando que todas las reservas han sido eliminadas correctamente
     */
    @DeleteMapping("/deleteAllReservations")
    public String deleteAllreservations() {
        reservationService.deleteAllReservations();
        return "Se han borrado todas las reservas";
    }


    /**
     * Método que sirve para seleccionar una reserva por su código identificativo
     * @param id Código identificativo de una reserva concreta
     * @return Devuelve la reserva correspondiente al id seleccionado
     */
    @GetMapping("/getReservation")
    public Reservation getReservation(@RequestParam("id") UUID id) {
        System.out.println(id);
        return reservationService.getReservationById(id);
    }


    // TARIFAS HABITACIONES

    /**
     * Método que sirve para obtener todas las tarifas de habitaciones existentes de la aplicación
     * @return Devuelve todas las tarifas de habitaciones que existen en la aplicación
     */
    @GetMapping("/tarifas")
    public List<Tarifa> getTarifas() { return tarifaService.getAllTarifas(); }

    /**
     * Método que sirve para añadir una nueva tarifa de habitación a la aplicación
     * @param tarifa Tarifa de habitación que se desea añadir a la aplicación
     * @return Devueve la tarifa de habitación añadida a la aplicación para comprobar que no ha habido ningún error
     */
    @PostMapping("/insertTarifa")
    public Tarifa insertTarifa(@RequestBody Tarifa tarifa){ return tarifaService.addTarifa(tarifa); }

    /**
     * Método que sirve para eliminar una tarifa de habitación de la aplicación a través de su id
     * @param id código identificativo de la tarifa de habitación a eliminar
     * @return Devuelve un comentario avisando que la tarifa de habitación ha sido eliminado correctamente
     */
    @DeleteMapping("/deleteTarifa")
    public String deleteTarifaById(@RequestParam("id") Integer id) {
        System.out.println(id);
        tarifaService.deleteTarifa(id);
        return "Se ha borrado la tarifa escogida";
    }

    /**
     * Método para eliminar todas las tarifas de habitación existentes en la aplicación
     * @return Devuelve un comentario avisando que todas las tarifas de habitación han sido eliminadas correctamente
     */
    @DeleteMapping("/deleteAllTarifas")
    public String deleteAllTarifas() {
        tarifaService.deleteAllTarifas();
        return "Se han borrado todas las tarifas";
    }

    // TARIFAS SALAS

    /**
     * Método que sirve para obtener todas las tarifas de salas existentes de la aplicación
     * @return Devuelve todas las tarifas de salas que existen en la aplicación
     */
    @GetMapping("/tarifas_meeting_room")
    public List<TarifaMeetingRoom> getTarifasMeetingRoom() { return tarifaMeetingRoomService.getAllTarifasMeetingRoom();}

    /**
     * Método que sirve para añadir una nueva tarifa de sala a la aplicación
     * @param tarifa Tarifa de sala que se desea añadir a la aplicación
     * @return Devueve la tarifa de sala añadida a la aplicación para comprobar que no ha habido ningún error
     */
    @PostMapping("/insertTarifa_meeting_room")
    public TarifaMeetingRoom insertTarifaMeetingRoom(@RequestBody TarifaMeetingRoom tarifa){
        return tarifaMeetingRoomService.addTarifaMeetingRoom(tarifa); }

    /**
     * Método que sirve para eliminar una tarifa de sala de la aplicación a través de su id
     * @param id código identificativo de la tarifa de sala a eliminar
     * @return Devuelve un comentario avisando que la tarifa de sala ha sido eliminado correctamente
     */
    @DeleteMapping("/deleteTarifa_meeting_room")
    public String deleteTarifaMeetingRoomById(@RequestParam("id") Integer id) {
        System.out.println(id);
        tarifaMeetingRoomService.deleteTarifaMeetingRoomById(id);
        return "Se ha borrado la tarifa escogida";
    }

    /**
     * Método para eliminar todas las tarifas de sala existentes en la aplicación
     * @return Devuelve un comentario avisando que todas las tarifas de sala han sido eliminadas correctamente
     */
    @DeleteMapping("/deleteAllTarifas_meeting_room")
    public String deleteAllTarifaMeetingRoom() {
        tarifaMeetingRoomService.deleteAllTarifasMeetingRoom();
        return "Se han borrado todas las tarifas";
    }


    // ROOMS

    /**
     * Método que sirve para obtener todas las habitaciones y salas existentes de la aplicación
     * @return Devuelve todas las habitaciones y salas que existen en la aplicación
     */
    @GetMapping("/rooms")
    public List<Room> getRooms() { return roomService.getAllRooms(); }

    /**
     * Método que sirve para eliminar una habitación o sala de la aplicación a través de su id
     * @param id código identificativo de la sala o habitación a eliminar
     * @return Devuelve un comentario avisando que la sala o habitación ha sido eliminada correctamente
     */
    @DeleteMapping("/deleteRooms")
    public String deleteRoomById(@RequestParam("id") UUID id) {
        System.out.println(id);
        roomService.deleteRoomById(id);
        return "Se ha borrado la habitación";
    }

    /**
     * Método que sirve para obtener todas las habitaciones existentes de la aplicación
     * @return Devuelve todas las habitaciones que existen en la aplicación
     */
    @GetMapping("/hotelRooms")
    public List<HotelRoom> getHotelRooms() { return roomService.getAllHotelRooms(); }

    /**
     * Método que sirve para obtener todas las salas existentes de la aplicación
     * @return Devuelve todas las salas que existen en la aplicación
     */
    @GetMapping("/meetingRooms")
    public List<MeetingRoom> getMeetingRooms() { return roomService.getAllMeetingRooms();}

    /**
     * Método que sirve para añadir una nueva habitación a la aplicación
     * @param room Habitación de hotel a añadir a la aplicación
     * @return Devuelve la habitación añadida en la aplicación para demostrar que se ha añadido correctamente
     */
    @PostMapping("/insertHotelRoom")
    public HotelRoom insertHotelRoom(@RequestBody HotelRoom room) { return roomService.addHotelRoom(room); }

    /**
     * Método que sirve para añadir una nueva sala a la aplicación
     * @param room Sala a añadir a la aplicación
     * @return Devuelve la sala añadida en la aplicación para demostrar que se ha añadido correctamente
     */
    @PostMapping("/insertMeetingRoom")
    public MeetingRoom insertMeetingRoom(@RequestBody MeetingRoom room) { return roomService.addMeetingRoom(room); }

    /**
     * Método que sirve para eliminar una habitación de la aplicación a través de su id
     * @param id código identificativo de la  habitación a eliminar
     * @return Devuelve un comentario avisando que la habitación ha sido eliminada correctamente
     */
    @DeleteMapping("/deleteHotelRoomById")
    public String deleteHotelRoomById(@RequestParam("id") UUID id) {
        System.out.println(id);
        roomService.deleteHotelRoomById(id);
        return "Se ha borrado la habitación de hotel escogida";
    }

    /**
     * Método que sirve para eliminar una sala de la aplicación a través de su id
     * @param id código identificativo de la sala a eliminar
     * @return Devuelve un comentario avisando que la sala ha sido eliminada correctamente
     */
    @DeleteMapping("/deleteMeetingRoomById")
    public String deleteMeetingRoomById(@RequestParam("id") UUID id) {
        System.out.println(id);
        roomService.deleteMeetingRoomById(id);
        return "Se ha borrado la sala de reuniones escogida";
    }

    /**
     * Método que sirve para eliminar todas las habitaciones de hotel existentes
     * @return Devuelve un comentario avisando que las habitaciones han sido eliminadaa correctamente
     */
    @DeleteMapping("/deleteAll_hotel_room")
    public String deleteAllHotelRoom() {
        roomService.deleteAllHotelRooms();
        return "Se han borrado todas las habitaciones de hotel";
    }

    /**
     * Método que sirve para eliminar todas las salas de hotel existentes
     * @return Devuelve un comentario avisando que las salas han sido eliminadaa correctamente
     */
    @DeleteMapping("/deleteAll_Meeting_room")
    public String deleteAllMeetingRoom() {
        roomService.deleteAllMeetingRooms();
        return "Se han borrado todas las salas de reuniones";
    }


    // USERS

    /**
     * Método que sirve para obtener todos los usuarios de la aplicación
     * @return Devuelve todos los usuarios de la aplicación
     */
    @GetMapping("/users")
    public List<User> getUsers() { return userService.getAllUsers(); }

    /**
     * Método que sirve para añadir un usuario en la aplicación
     * @param user Usuario que se va a añadir a ña aplicación
     * @return Devuelve el usuario añadido a la aplicación
     */
    @PostMapping("/insertUser")
    public User insertUser(@RequestBody User user) { return userService.createUser(user); }

}