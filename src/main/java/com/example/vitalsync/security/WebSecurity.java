package com.example.vitalsync.security;

import com.example.vitalsync.service.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity

public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UsuarioService usuarioService;

    public WebSecurity(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public WebSecurity() {
    }

    public WebSecurity(boolean disableDefaults) {
        super(disableDefaults);
    }

    public WebSecurity(boolean disableDefaults, UsuarioService usuarioService) {
        super(disableDefaults);
        this.usuarioService = usuarioService;
    }
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(usuarioService);
//        auth.setPasswordEncoder(passwordEncoder());
//        return auth;
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/vitalsync/usuario/create").permitAll()
                .antMatchers(HttpMethod.POST, "/vitalsync/usuario/create").permitAll()
                .antMatchers("/vitalsync/profesional/create").permitAll()
                .antMatchers(HttpMethod.POST, "/vitalsync/profesional/create").permitAll()
                .antMatchers("/vitalsync/usuario/login").permitAll()
                .antMatchers(HttpMethod.POST, "/vitalsync/usuario/login").permitAll()
                .antMatchers("/vitalsync/profesional/*").permitAll()
                .antMatchers("/vitalsync/paciente/*").permitAll()
//                .antMatchers(HttpMethod.GET, "/vitalsync/profesional/listar").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

}

