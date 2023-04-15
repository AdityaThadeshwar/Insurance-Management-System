package com.aditya.insurance.management.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //Authenticate all requests
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        //Show a login popup
        http.httpBasic(Customizer.withDefaults());

        //Disable CSRF for POST/PUT requests
        http.csrf().disable();

        return http.build();
    }
}
