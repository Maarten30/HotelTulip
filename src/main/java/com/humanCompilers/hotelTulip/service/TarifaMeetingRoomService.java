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

@Service
@NoArgsConstructor
public class TarifaMeetingRoomService {

    private TarifaMeetingRoomRepository tarifaMeetingRoomRepository;

    @Autowired
    public TarifaMeetingRoomService(TarifaMeetingRoomRepository tarifaMeetingRoomRepository) {
        this.tarifaMeetingRoomRepository =  tarifaMeetingRoomRepository;
    }

    public TarifaMeetingRoom addTarifaMeetingRoom(TarifaMeetingRoom tarifa) { return tarifaMeetingRoomRepository.save(tarifa); }

    public TarifaMeetingRoom getTarifaMeetingRoomById(Integer id) { return tarifaMeetingRoomRepository.findById(id).get(); }

    public List<TarifaMeetingRoom> getAllTarifasMeetingRoom() {

        Iterable <TarifaMeetingRoom> tarifasDB = tarifaMeetingRoomRepository.findAll();
        List<TarifaMeetingRoom> tarifas = new ArrayList<>();
        tarifasDB.forEach(tarifa -> {
            tarifas.add(tarifa);
        });
        return tarifas;
    }
    public int deleteTarifaMeetingRoomById(Integer id) { tarifaMeetingRoomRepository.deleteById(id); return 1;}

    public int deleteAllTarifasMeetingRoom() { tarifaMeetingRoomRepository.deleteAll(); return 1; }

    public TarifaMeetingRoom updateTarifa(Integer id, TarifaMeetingRoom newTarifa) {
        TarifaMeetingRoom db_tarifa = getTarifaMeetingRoomById(id);

        db_tarifa.setStarting_date(newTarifa.getStarting_date());
        db_tarifa.setEnding_date(newTarifa.getEnding_date());
        db_tarifa.setPrice(newTarifa.getPrice());
        db_tarifa.setSeason(newTarifa.getSeason());

        return tarifaMeetingRoomRepository.save(db_tarifa);
    }


    public TarifaMeetingRoom calculateMeetingRoomTarifa(LocalDate date, MeetingRoom room) {

        // Saca la tarifa, mirando si la fecha esta entre las dos fechas que limitan la tarifa y
        // tambien comparando el tipo de habitacion
        Iterable<TarifaMeetingRoom> tarifas_db = tarifaMeetingRoomRepository.findAll(); // Convertir a List
        List<TarifaMeetingRoom> tarifas = new ArrayList<>();
        tarifas_db.forEach(t-> {
            tarifas.add(t);
        });
        // Saca la tarifa, mirando si la fecha esta entre las dos fechas que limitan la tarifa y
        // tambien comparando el tipo de habitacion
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
