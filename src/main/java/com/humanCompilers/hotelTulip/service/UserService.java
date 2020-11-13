package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.UserRepository;
import com.humanCompilers.hotelTulip.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Esta entrando aqui");
        User active_user =  userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
        System.out.println(active_user);

        active_user.setAccountNonExpired(true);
        active_user.setAccountNonLocked(true);
        active_user.setCredentialsNonExpired(true);
        active_user.setEnabled(true);

        return active_user;
    }

    public User createUser (User newUser)
    {
        if(!userRepository.existsById(newUser.getUsername())){
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            return userRepository.save(newUser);
        } else {
            return null;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Iterable<User> db_users = userRepository.findAll();

        db_users.forEach(r -> {
            users.add(r);
        });
        return users;
    }
}
