package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.User;

import java.util.List;
import java.util.Optional;


public interface UserDao {

    // Metodos para simular la BD, insertar y sacar usuarios.
    Optional<User> selectApplicationUserByUsername(String email);
    public void addUser (User newUser);

}
