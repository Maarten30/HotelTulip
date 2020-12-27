package com.humanCompilers.hotelTulip.controller;

import com.humanCompilers.hotelTulip.service.ReservationService;
import com.humanCompilers.hotelTulip.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Clase que responde a las peticiones http relativas a las estadísticas
 * @author HumanCompilers
 */
@Controller
@RequestMapping("statistic")
public class GraphController {

    private final StatisticsService statisticsService;
    private final ReservationService reservationService;

    /**
     * Constructor de la clase
     * @param statisticsService Instancia de la clase StatisticsService para poder hacerle llamadas
     * @param reservationService Instancia de la clase ReservationService para poder hacerle llamadas
     */
    @Autowired
    public GraphController(StatisticsService statisticsService, ReservationService reservationService)
    {
        this.statisticsService = statisticsService;
        this.reservationService = reservationService;
    }

    /**
     * Método al que se le llama al realizar una petición Get con la url de /statistics
     * @return Devuelve la vista correspondiente a la estadística
     */
    @GetMapping("/viewCharts")
    public ModelAndView statistics() {

        ModelAndView modelAndView = new ModelAndView("statistics");

        return modelAndView;
    }


}
