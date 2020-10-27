package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.Tarifa;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fakeTarifaDao")
public class FakeTarifaDataAccessService implements TarifaDao {

    private static List<Tarifa> tarifa_DB = new ArrayList<>();


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
