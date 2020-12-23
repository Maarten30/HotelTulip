package com.humanCompilers.hotelTulip.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase utilizada para crear objetos que encriptan las contraseñas de los usuarios
 */
@Configuration
public class PasswordConfig {
    /**
     * Metodo al que se le llama para obtener un objeto codificador de contraseñas
     * @return objeto codificador de contraseñas
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
