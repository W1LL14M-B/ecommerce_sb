package com.william.products.service.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
/* 
@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/usuarios/**").permitAll() // Permitir acceso sin autenticación
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> httpBasic.disable()); // Deshabilitar autenticación básica si no la usas

        return http.build();
    }
}  */



@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF (para desarrollo)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").permitAll() // Permite acceso a todas las rutas
            )
            .formLogin(login -> login.disable()) // Deshabilita formulario de login
            .httpBasic(basic -> basic.disable()); // Deshabilita autenticación básica

        return http.build();
    }
}