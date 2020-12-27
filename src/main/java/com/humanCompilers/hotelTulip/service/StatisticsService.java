package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Clase que proporciona la lógica de negocio relacionada con las estadísticas
 * @HumanCompilers
 */
@Service
public class StatisticsService
{
    private final ReservationService reservationService;

    /**
     * Constructor de la clase
     * @param reservationService instancia de la clase ReservationService para poder hacerle llamadas
     */
    @Autowired
    public StatisticsService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Método que sirve para obtener el número de reservas existentes en un mes de la base de datos
     * @return número de reservas que hay de cada mes en la base de datos
     */
    public HashMap<Integer, Integer> getNumReservations() {
        List<Reservation> reservations =  reservationService.getAllReservations();

        int year = Calendar.getInstance().get(Calendar.YEAR);

        HashMap<Integer, Integer> data = new HashMap<>();

        for(int i=0; i<reservations.size(); i++) {
            if(reservations.get(i).getCheckinDate().getYear()==year)
            {
                int month = reservations.get(i).getCheckinDate().getMonthValue();
                int aux = data.getOrDefault(month, 0);
                data.put(month, aux+1);
            }

        }
        return data;
    }

}
