package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.ReservationRepository;
import com.humanCompilers.hotelTulip.dao.UserRepository;
import com.humanCompilers.hotelTulip.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    static UserService userService;

    static UserRepository userRepository = mock((UserRepository.class));
    static PasswordEncoder passwordEncoder;

    static User user_1;
    static User user_2;

    @BeforeAll
    public static void init(){
        userService = new UserService(userRepository, passwordEncoder);

        user_1 = new User();
        user_1.setFirstName("Luis");
        user_1.setLastName("Aranzabal");
        user_1.setUsername("luisaran");

        user_2= new User();
        user_2.setFirstName("Gabri");
        user_2.setLastName("Garaizabal");
        user_2.setUsername("gabrigara");

    }

    @Test
    void getAllUsers() {

        when(userRepository.findAll()).thenReturn(Stream.of(
                user_1, user_2
        ).collect(Collectors.toList()));

        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    void createUserNull() {
        when(userRepository.existsById(any())).thenReturn(true);

        assertEquals(null, userService.createUser(user_1));
    }
    //Faltaría el caso en que devuelve false y se crea el usuario
}