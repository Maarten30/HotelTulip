package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.User;

import java.util.List;


public interface UserDao {

    // Metodos para simular la BD, insertar y sacar usuarios.
    int insertUser(User user);
    List<User> selectAllUser();

}
