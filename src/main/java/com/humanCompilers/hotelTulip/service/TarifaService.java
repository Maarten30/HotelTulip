package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.TarifaMeetingRoomRepository;
import com.humanCompilers.hotelTulip.dao.TarifaRepository;
import com.humanCompilers.hotelTulip.model.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que proporciona la lógica de negocio relacionada con las tarifas de habitación
 * @HumanCompilers
 */
@Service
@NoArgsConstructor
public class TarifaService {

    private TarifaRepository tarifaRepository;
    private TarifaMeetingRoomRepository tarifaMeetingRoomRepository;

    /**
     * Contructor de la clase
     * @param tarifaRepository Instancia de la clase tarifaRepository para poder hacerle llamadas
     */
    @Autowired
    public TarifaService(TarifaRepository tarifaRepository) {
        this.tarifaRepository =  tarifaRepository;
    }

    /**
     * Método que sirve para añadir una tarifa de habitación en la base de datos
     * @param tarifa tarifa a guardar en la base de datos
     */
    public Tarifa addTarifa(Tarifa tarifa) { return tarifaRepository.save(tarifa); }

    /**
     * Método que sirve para obtener una tarifa de habitación por id de la base de datos
     * @param id identificador de la tarifa a obtener
     */
    public Tarifa getTarifaById(Integer id) { return tarifaRepository.findById(id).get(); }

    /**
     * Método para obtener una lista con todas las tarifas de habitación de la base de datos
     * @return lista con las tarifas de habitación
     */
    public List<Tarifa> getAllTarifas() {

        Iterable <Tarifa> tarifasDB = tarifaRepository.findAll();
        List<Tarifa> tarifas = new ArrayList<>();
        tarifasDB.forEach(tarifa -> {
            tarifas.add(tarifa);
        });
        return tarifas;
    }

    /**
     *Método para eliminar una tarifa de habitación de la base de datos por id
     * @param id identificador de la tarifa a eliminar
     */
    public int deleteTarifa(Integer id) { tarifaRepository.deleteById(id); return 1;}

    /**
     * Método que sirve para eliminar todas las tarifas de habitación de la base de datos
     */
    public int deleteAllTarifas() { tarifaRepository.deleteAll(); return 1; }

    /**
     * Método que sirve para actualizar una tarifa de habitación de la base de datos
     * @param id identificador de la tarifa a actualizar
     * @param newTarifa nueva tarifa a insertar en la base de datos
     */
    public Tarifa updateTarifa(Integer id, Tarifa newTarifa) {
        Tarifa db_tarifa = getTarifaById(id);

        db_tarifa.setStarting_date(newTarifa.getStarting_date());
        db_tarifa.setEnding_date(newTarifa.getEnding_date());
        db_tarifa.setPrice(newTarifa.getPrice());
        db_tarifa.setSeason(newTarifa.getSeason());

        return tarifaRepository.save(db_tarifa);
    }

    /**
     * Método que sirve para calcular la tarifa de una fecha y una habitación concreta
     * @param date fecha
     * @param room habitación
     * @return devuelve la tarifa correspondiente a una habitación y una fecha
     */
    public Tarifa calculateHotelRoomTarifa(LocalDate date, HotelRoom room) {

        Iterable<Tarifa> tarifas_db = tarifaRepository.findAll();
        List<Tarifa> tarifas = new ArrayList<>();
        tarifas_db.forEach(t-> {
            tarifas.add(t);
        });
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