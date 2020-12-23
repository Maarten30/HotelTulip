package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.TarifaMeetingRoomRepository;
import com.humanCompilers.hotelTulip.model.MeetingRoom;
import com.humanCompilers.hotelTulip.model.Tarifa;
import com.humanCompilers.hotelTulip.model.TarifaMeetingRoom;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que proporciona la lógica de negocio relacionada con las tarifas de sala
 * @author HumanCompilers
 */
@Service
@NoArgsConstructor
public class TarifaMeetingRoomService {

    private TarifaMeetingRoomRepository tarifaMeetingRoomRepository;

    /**
     * Constructor de la clase
     * @param tarifaMeetingRoomRepository Instancia de la clase tarifaMeetingRoomRepository para poder hacerle llamadas
     */
    @Autowired
    public TarifaMeetingRoomService(TarifaMeetingRoomRepository tarifaMeetingRoomRepository) {
        this.tarifaMeetingRoomRepository =  tarifaMeetingRoomRepository;
    }

    /**
     * Método que sirve para añadir la tarifa de una sala a la base de datos
     * @param tarifa tarifa de sala a añadir a la base de datos
     */
    public TarifaMeetingRoom addTarifaMeetingRoom(TarifaMeetingRoom tarifa) { return tarifaMeetingRoomRepository.save(tarifa); }

    /**
     * Método que sirve para obtener una tarifa de sala por id
     * @param id identificador de la sala a obtener
     */
    public TarifaMeetingRoom getTarifaMeetingRoomById(Integer id) { return tarifaMeetingRoomRepository.findById(id).get(); }

    /**
     * Método para obtener todas las tarifas de las salas existentes en la base de datos
     * @return lista con todas las tarifas de las salas existentes
     */
    public List<TarifaMeetingRoom> getAllTarifasMeetingRoom() {

        Iterable <TarifaMeetingRoom> tarifasDB = tarifaMeetingRoomRepository.findAll();
        List<TarifaMeetingRoom> tarifas = new ArrayList<>();
        tarifasDB.forEach(tarifa -> {
            tarifas.add(tarifa);
        });
        return tarifas;
    }

    /**
     * Método que sirve para eliminar la tarifa de una sala por id
     * @param id identificador de la tarifa de la sala a eliminar
     */
    public int deleteTarifaMeetingRoomById(Integer id) { tarifaMeetingRoomRepository.deleteById(id); return 1;}

    /**
     * Método que sirve para eliminar todas las tarifas de salas existentes de la base de datos
     */
    public int deleteAllTarifasMeetingRoom() { tarifaMeetingRoomRepository.deleteAll(); return 1; }

    /**
     * Método que sirve para actualizar la tarifa de una sala
     * @param id identificador de la tarifa de sala a actualizar
     * @param newTarifa nueva tarifa de sala a insertar en la base de datos
     */
    public TarifaMeetingRoom updateTarifa(Integer id, TarifaMeetingRoom newTarifa) {
        TarifaMeetingRoom db_tarifa = getTarifaMeetingRoomById(id);

        db_tarifa.setStarting_date(newTarifa.getStarting_date());
        db_tarifa.setEnding_date(newTarifa.getEnding_date());
        db_tarifa.setPrice(newTarifa.getPrice());
        db_tarifa.setSeason(newTarifa.getSeason());

        return tarifaMeetingRoomRepository.save(db_tarifa);
    }

    /**
     * Método que sirve para calcular la tarifa correspodiente a una fecha y sala concreta
     * @param date fecha
     * @param room sala
     * @return tarifa corresponiente a fecha y sala
     */
    public TarifaMeetingRoom calculateMeetingRoomTarifa(LocalDate date, MeetingRoom room) {

        Iterable<TarifaMeetingRoom> tarifas_db = tarifaMeetingRoomRepository.findAll(); // Convertir a List
        List<TarifaMeetingRoom> tarifas = new ArrayList<>();
        tarifas_db.forEach(t-> {
            tarifas.add(t);
        });
        // Saca la tarifa, mirando si la fecha esta entre las dos fechas que limitan la tarifa y
        // tambien comparando el tipo de sala
        TarifaMeetingRoom tarifa_sacada = tarifas.stream()
                .filter(t -> date.isAfter(t.getStarting_date()) &&
                        date.isBefore(t.getEnding_date())&&
                        t.getRoom_type().equals(room.getMeetingRoomType()))
                .findFirst()
                .orElse(null);

        // Si es null, puede ser porque la fecha coincide justo con la fecha inicio o fin de la tarifa
        if(tarifa_sacada == null) {
            tarifa_sacada = tarifas.stream()
                    .filter(t -> (date.equals(t.getStarting_date()) ||
                            date.equals(t.getEnding_date())) &&
                            room.getMeetingRoomType().equals(t.getRoom_type()))
                    .findFirst()
                    .orElse(null);
        }
        return tarifa_sacada;
    }
}
