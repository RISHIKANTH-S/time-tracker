package com.example.TimeTracker.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF since you're using JWT
                .csrf(csrf -> csrf.disable())

                // Make session stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Permit unauthenticated endpoints like register/login
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/api/employees/register").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
