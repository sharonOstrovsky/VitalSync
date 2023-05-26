package com.example.vitalsync.security;

import com.example.vitalsync.config.SecurityConfig;
import com.example.vitalsync.service.serviceImpl.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UsuarioServiceImpl usuarioService;
    private SecurityConfig securityConfig;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(securityConfig.passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/vitalsync/usuario/create").permitAll()
//                .antMatchers(HttpMethod.POST, "/vitalsync/usuario/create").permitAll()
//                .antMatchers("/vitalsync/profesional/create").permitAll()
//                .antMatchers(HttpMethod.POST, "/vitalsync/profesional/create").permitAll()
//                .antMatchers("/vitalsync/usuario/login").permitAll()
//                .antMatchers(HttpMethod.POST, "/vitalsync/usuario/login").permitAll()
//                .antMatchers("/vitalsync/profesional/*").permitAll()
//                .antMatchers("/vitalsync/paciente/*").permitAll()
//
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();
        http.authorizeRequests().antMatchers("swagger-ui/index.html#").permitAll();
    }
}