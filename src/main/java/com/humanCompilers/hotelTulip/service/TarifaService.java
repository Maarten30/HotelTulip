package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.TarifaDao;
import com.humanCompilers.hotelTulip.model.Tarifa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

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

}
