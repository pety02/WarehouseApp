package com.example.warehouseapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // disable CSRF for API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/employees").permitAll()          // registration
                        .requestMatchers("/api/employees/login").permitAll()    // login
                        .requestMatchers("/api/employees/**").authenticated() // other employee endpoints
                        .anyRequest().permitAll() // all other endpoints
                )
                .httpBasic(AbstractHttpConfigurer::disable); // disable browser popup

        return http.build();
    }
}