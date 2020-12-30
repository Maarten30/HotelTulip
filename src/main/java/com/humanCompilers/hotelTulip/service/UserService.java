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

/**
 * Clase que proporciona la lógica de negocio relacionada con los usuarios
 * @HumanCompilers
 */
@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor de la clase
     * @param userRepository Instancia de la clase userRepository para poder hacerle llamadas
     * @param passwordEncoder parametro para codificar contraseñas
     */
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Método que sirve para conocer al usuario que está logeado
     * @param username email del usuario logeado
     * @return devuelve usuario logeado
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User active_user =  userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));

        active_user.setAccountNonExpired(true);
        active_user.setAccountNonLocked(true);
        active_user.setCredentialsNonExpired(true);
        active_user.setEnabled(true);

        return active_user;
    }

    /**
     * Método que sirve para crear un nuevo usuario
     * @param newUser nuevo usuario a crear
     */
    public User createUser (User newUser)
    {
        if(!userRepository.existsById(newUser.getUsername())){
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            return userRepository.save(newUser);
        } else {
            return null;
        }
    }

    /**
     * Método que sirve para obtener todos los usuarios de la base de datos
     * @return lista de todos los usuarios
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Iterable<User> db_users = userRepository.findAll();

        db_users.forEach(r -> {
            users.add(r);
        });
        return users;
    }
}
