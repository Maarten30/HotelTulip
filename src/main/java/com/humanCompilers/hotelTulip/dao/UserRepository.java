package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

        //public abstract List<User> findUsersByEmail(String email);


}
