package com.tarefa.agenda.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.csrf().disable()
                .authorizeHttpRequests().
                requestMatchers("/", "/register", "/signin").permitAll()
                .requestMatchers("/user/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/userLogin")
                .defaultSuccessUrl("/user/profile").permitAll();

        return security.build();
    }
}
