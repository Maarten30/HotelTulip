package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.TarifaDao;
import com.humanCompilers.hotelTulip.model.Room;
import com.humanCompilers.hotelTulip.model.Tarifa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TarifaService {

    private final TarifaDao tarifaDao;

    @Autowired
    public TarifaService(@Qualifier("fakeTarifaDao") TarifaDao tarifaDao) {
        this.tarifaDao = tarifaDao;
    }

    public int addTarifa(Tarifa tarifa) {
        return tarifaDao.insertTarifa(tarifa);
    }

    public List<Tarifa> getAllTarifas() {
        return tarifaDao.selectAllTarifas();
    }


    public static Tarifa calcularTarifa(LocalDate date, List<Tarifa> tarifas, Room room) {

        // Saca la tarifa, mirando si la fecha esta entre las dos fechas que limitan la tarifa y
        // tambien comparando el tipo de habitacion
        Tarifa tarifa_sacada = tarifas.stream()
                .filter(t -> date.isAfter(t.getStarting_date()) &&
                        date.isBefore(t.getEnding_date())&&
                        t.getRoom_type().equals(room.getType()))
                .findFirst()
                .orElse(null);

        // Si es null, puede ser porque la fecha coincide justo con la fecha inicio o fin de la tarifa
        if(tarifa_sacada == null) {
            tarifa_sacada = tarifas.stream()
                    .filter(t -> (date.equals(t.getStarting_date()) ||
                            date.equals(t.getEnding_date())) &&
                            room.getType().equals(t.getRoom_type()))
                    .findFirst()
                    .orElse(null);
        }
        return tarifa_sacada;
    }

}
