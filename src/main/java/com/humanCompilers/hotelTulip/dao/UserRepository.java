package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz a través la cual se interactua con la parte de la base de datos relativa a los usuarios
 * @author HumanCompilers
 */
public interface UserRepository extends CrudRepository<User, String> {

}
