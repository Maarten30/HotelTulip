package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.Tarifa;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz a través la cual se interactua con la parte de la base de datos relativa a las tarofas de habitaciones
 * @author HumanCompilers
 */
public interface TarifaRepository extends CrudRepository<Tarifa, Integer> {
}
