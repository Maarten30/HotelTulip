package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.TarifaRepository;
import com.humanCompilers.hotelTulip.model.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class TarifaService {

    private TarifaRepository tarifaRepository;

    @Autowired
    public TarifaService(TarifaRepository tarifaRepository) {
        this.tarifaRepository =  tarifaRepository;
    }

    public Tarifa addTarifa(Tarifa tarifa) { return tarifaRepository.save(tarifa); }

    public Tarifa getTarifaById(Integer id) { return tarifaRepository.findById(id).get(); }

    public List<Tarifa> getAllTarifas() {

        Iterable <Tarifa> tarifasDB = tarifaRepository.findAll();
        List<Tarifa> tarifas = new ArrayList<>();
        tarifasDB.forEach(tarifa -> {
            tarifas.add(tarifa);
        });
        return tarifas;
    }

    public int deleteTarifa(Integer id) { tarifaRepository.deleteById(id); return 1;}

    public int deleteAllTarifas() { tarifaRepository.deleteAll(); return 1; }

    public Tarifa updateTarifa(Integer id, Tarifa newTarifa) {
        Tarifa db_tarifa = getTarifaById(id);

        db_tarifa.setStarting_date(newTarifa.getStarting_date());
        db_tarifa.setEnding_date(newTarifa.getEnding_date());
        db_tarifa.setPrice(newTarifa.getPrice());
        db_tarifa.setSeason(newTarifa.getSeason());

        return tarifaRepository.save(db_tarifa);
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

    public void tellMeSometing(int i) {

        if (i < 5) {
            System.out.println("Lower than 5");
            return;
        }

        if (i % 2 == 0) {
            System.out.println("Even number.");
            return;
        }
    }
}