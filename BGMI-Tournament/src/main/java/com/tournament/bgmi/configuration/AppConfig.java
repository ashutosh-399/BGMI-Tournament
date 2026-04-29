package com.tournament.bgmi.configuration;

import com.tournament.bgmi.filter.AppFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppConfig {


    @Autowired
    private AppFilter appFiletr;

    @Bean
    public SecurityFilterChain security(HttpSecurity http){
        http.authorizeHttpRequests((req -> req
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/user/**").hasAnyRole("ADMIN", "PLAYER")
                .anyRequest().authenticated()))
                .csrf(csrf -> csrf.disable());
        return http.addFilterBefore(appFiletr, UsernamePasswordAuthenticationFilter.class).build();
    }




    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
        return config.getAuthenticationManager();
    }
}
