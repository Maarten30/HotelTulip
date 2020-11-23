package com.humanCompilers.hotelTulip.service;

import com.humanCompilers.hotelTulip.dao.UserRepository;
import com.humanCompilers.hotelTulip.model.User;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    static UserService userService;
    static UserRepository userRepository = mock((UserRepository.class));
    static PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    static User user_1;
    static User user_2;


    @BeforeAll
    public static void init(){
        // Initialize UserService
        userService = new UserService(userRepository, passwordEncoder);

        // Create User 1
        user_1 = new User();
        user_1.setFirstName("Luis");
        user_1.setLastName("Aranzabal");
        user_1.setUsername("luisaran");

        // Create User 2
        user_2 = new User();
        user_2.setFirstName("Gabri");
        user_2.setLastName("Garaizabal");
        user_2.setUsername("gabrigara");
    }

    @Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 1200, average = 250)
    void getAllUsers() {

        when(userRepository.findAll()).thenReturn(Stream.of(
                user_1, user_2
        ).collect(Collectors.toList()));

        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 1200, average = 250)
    void createUser() {
        when(userRepository.existsById(any())).thenReturn(false);
        when(userRepository.save(user_1)).thenReturn(user_1);
        when(passwordEncoder.encode(anyString())).thenReturn(anyString());
        User usuario_prueba = userService.createUser(user_1);
        assertEquals(user_1, usuario_prueba);
    }

    @Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 1200, average = 250)
    void createUserNull() {
        when(userRepository.existsById(any())).thenReturn(true);

        assertEquals(null, userService.createUser(user_1));
    }

    @Test
    @PerfTest(invocations = 1000, threads = 20)
    @Required(max = 1200, average = 250)
    void loadUserByUsername() {
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user_1));

        assertEquals(user_1.getUsername(), userService.loadUserByUsername("Luis").getUsername());
    }
}