package com.company.ForoHub.infra.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfiguracionDeSeguridad {

    @Autowired
    private FiltroSeguridad filtroSeguridad;

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return   httpSecurity.csrf(csrf -> csrf.disable()).sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))//le indicamos a spirng el tipo de sesion
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/login")
                        .permitAll()
                        .requestMatchers("/swagger-ui.html", "/v3/api-docs/**","/swagger-ui./**" +
                                "").permitAll()
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(filtroSeguridad, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
