package com.humanCompilers.hotelTulip.security;

import com.humanCompilers.hotelTulip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Clase de configuración para el sistema de autenticación de los usuarios
 * @author HumanCompilers
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    /**
     * Constructor de la clase
     * @param passwordEncoder Instancia de la clase passwordEncoder
     * @param userService Instancia del servicio de usuarios
     */
    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    /**
     * Método que configura el nivel y sistema de seguridad utilizado en la página web
     * @param http Objeto HttpSecurity sobre el que se realiza la configuración
     * @throws Exception Lanza una excepción si algo falla
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/reservations/**").authenticated()
                //.antMatchers("/api/**").authenticated()
                .and()
                .formLogin()
                    .loginPage("/user/login")
                    .permitAll()
                    .defaultSuccessUrl("/")
                    .passwordParameter("password")
                    .usernameParameter("email")
                .and()
                .logout()
                    .logoutUrl("/user/logout")
                    .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .and()
                .httpBasic();

    }

    /**
     * Método que establece la fuente de la información que se va utilizar en la autenticación
     * @param auth Objeto AuthtenticationManagerBuilder que se va configurar
     * @throws Exception Lanza una excepción si algo falla
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * Crea un objeto DaoAuthenticationProvider que se va utilizar como fuente de información
     * @return DaoAuthenticationProvider con un codificador de contraseñas y el servicio de usuarios propio asignado
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);

        return provider;
    }

}


