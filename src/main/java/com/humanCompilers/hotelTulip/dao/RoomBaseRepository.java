package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

/**
 * Interfaz a través la cual se interactua con la parte de la base de datos relativa a las habitaciones y salas
 * @author HumanCompilers
 */
@NoRepositoryBean
public interface RoomBaseRepository<T extends Room> extends CrudRepository<T, UUID> {


}
