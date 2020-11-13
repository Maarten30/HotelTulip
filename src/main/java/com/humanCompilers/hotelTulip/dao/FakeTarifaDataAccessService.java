package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.HotelRoomType;
import com.humanCompilers.hotelTulip.model.Season;
import com.humanCompilers.hotelTulip.model.Tarifa;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository("fakeTarifaDao")
public class FakeTarifaDataAccessService implements TarifaDao {

    private static List<Tarifa> tarifa_DB = new ArrayList<>();

    public FakeTarifaDataAccessService() {
        // LLeno el array de tarifas inventadas para simular una BD
        LocalDate date1 = LocalDate.of(2020, 1, 1);
        LocalDate date2 = LocalDate.of(2020, 5, 31);
        LocalDate date3 = LocalDate.of(2020, 6, 1);
        LocalDate date4 = LocalDate.of(2020, 8, 31);
        LocalDate date5 = LocalDate.of(2020, 9, 1);
        LocalDate date6 = LocalDate.of(2020, 12, 31);

        Tarifa tarifa1_1 = new Tarifa(Season.MID, date1, date2, HotelRoomType.SINGLE, 50.0);
        Tarifa tarifa1_2 = new Tarifa(Season.MID, date1, date2, HotelRoomType.DOUBLE, 50.0);
        Tarifa tarifa1_3 = new Tarifa(Season.MID, date1, date2, HotelRoomType.TRIPLE, 50.0);

        Tarifa tarifa2_1 = new Tarifa(Season.HIGH, date3, date4, HotelRoomType.SINGLE, 60.0);
        Tarifa tarifa2_2 = new Tarifa(Season.HIGH, date3, date4, HotelRoomType.DOUBLE, 60.0);
        Tarifa tarifa2_3 = new Tarifa(Season.HIGH, date3, date4, HotelRoomType.TRIPLE, 60.0);

        Tarifa tarifa3_1 = new Tarifa(Season.LOW, date5, date6, HotelRoomType.SINGLE, 70.0);
        Tarifa tarifa3_2 = new Tarifa(Season.LOW, date5, date6, HotelRoomType.DOUBLE, 70.0);
        Tarifa tarifa3_3 = new Tarifa(Season.LOW, date5, date6, HotelRoomType.TRIPLE, 70.0);

        tarifa_DB.add(tarifa1_1);
        tarifa_DB.add(tarifa1_2);
        tarifa_DB.add(tarifa1_3);

        tarifa_DB.add(tarifa2_1);
        tarifa_DB.add(tarifa2_2);
        tarifa_DB.add(tarifa2_3);

        tarifa_DB.add(tarifa3_1);
        tarifa_DB.add(tarifa3_2);
        tarifa_DB.add(tarifa3_3);
    }

    @Override
    public int insertTarifa(Tarifa tarifa) {
        tarifa_DB.add(tarifa);
        return 1;
    }

    @Override
    public List<Tarifa> selectAllTarifas() {
        return tarifa_DB;
    }
}
