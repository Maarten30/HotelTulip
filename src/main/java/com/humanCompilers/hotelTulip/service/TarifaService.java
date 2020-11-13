package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.TarifaDao;
import com.humanCompilers.hotelTulip.dao.TarifaRepository;
import com.humanCompilers.hotelTulip.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TarifaService {

    private final TarifaRepository tarifaRepository;

    @Autowired
    public TarifaService(TarifaRepository tarifaRepository) {
        this.tarifaRepository =  tarifaRepository;
    }

    public int addTarifa(Tarifa tarifa) {

        tarifaRepository.save(tarifa);
        return 1;
    }

    public List<Tarifa> getAllTarifas() {

        Iterable <Tarifa> tarifasDB = tarifaRepository.findAll();
        List<Tarifa> tarifas = new ArrayList<>();
        tarifasDB.forEach(tarifa -> {
            tarifas.add(tarifa);
        });
        return tarifas;
    }


    public static Tarifa calculateHotelRoomTarifa(LocalDate date, List<Tarifa> tarifas, HotelRoom room) {

        // Saca la tarifa, mirando si la fecha esta entre las dos fechas que limitan la tarifa y
        // tambien comparando el tipo de habitacion
        Tarifa tarifa_sacada = tarifas.stream()
                .filter(t -> date.isAfter(t.getStarting_date()) &&
                        date.isBefore(t.getEnding_date())&&
                        t.getRoom_type().equals(room.getHotelRoomType()))
                .findFirst()
                .orElse(null);

        // Si es null, puede ser porque la fecha coincide justo con la fecha inicio o fin de la tarifa
        if(tarifa_sacada == null) {
            tarifa_sacada = tarifas.stream()
                    .filter(t -> (date.equals(t.getStarting_date()) ||
                            date.equals(t.getEnding_date())) &&
                            room.getHotelRoomType().equals(t.getRoom_type()))
                    .findFirst()
                    .orElse(null);
        }
        return tarifa_sacada;
    }

}