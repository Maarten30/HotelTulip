package com.humanCompilers.hotelTulip.controller;

import com.humanCompilers.hotelTulip.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Clase que responde a las peticiones http relativas a las estadísticas
 * @author HumanCompilers
 */
@RequestMapping("data")
@RestController
public class DataApiController {

    private final StatisticsService statisticsService;

    /**
     * Constructor de la clase
     * @param statisticsService Instancia de la clase StatisticsService para poder hacerle llamadas
     */
    @Autowired
    public DataApiController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * Método al que se le llama al realizar una petición Get con la url de /charts-data
     * @return Devuelve el número de reservas asociado a cada mes
     */
    @GetMapping("/charts-data")
    public HashMap<Integer, Integer> getReservations()
    {
        HashMap<Integer, Integer> data = statisticsService.getNumReservations();

        return data;
    }
}
