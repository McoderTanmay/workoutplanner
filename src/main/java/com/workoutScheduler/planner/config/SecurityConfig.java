package com.workoutScheduler.planner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csfr -> csfr.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("api/auth/login", "api/auth/signup").permitAll()
                                .anyRequest().authenticated()
                        )
                .build();
    }
}
