package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.UserDao;
import com.humanCompilers.hotelTulip.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao user) {
        this.userDao = user;
    }

    public int addUser (User user)
    {
        return userDao.insertUser(user);
    }
    public List<User> getUsers ()
    {
        return userDao.selectAllUser();
    }
}
