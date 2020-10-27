package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.Tarifa;

import java.util.List;


public interface TarifaDao {

    int insertTarifa(Tarifa tarifa);


    List<Tarifa> selectAllTarifas();

}
