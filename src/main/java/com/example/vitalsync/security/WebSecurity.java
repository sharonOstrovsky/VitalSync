package com.example.vitalsync.security;
import com.example.vitalsync.service.serviceImpl.UsuarioServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Configuration
@EnableWebSecurity
//@AllArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {

//    private UsuarioService usuarioService;
//    private SecurityConfig securityConfig;
    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioServiceImpl();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Max")
                .password(passwordEncoder().encode("1234"))
                .authorities("ROL_ADMIN");
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/vitalsync/usuario/*").permitAll()
                .antMatchers("/vitalsync/usuario/prueba2").hasRole("PROFESIONAL")
                .antMatchers("/vitalsync/usuario/prueba3").hasRole("PACIENTE")
                .antMatchers("/vitalsync/usuario/pag").permitAll()
                .antMatchers("/vitalsync/admin/*").hasRole("ADMIN")
                .antMatchers("/vitalsync/profesional/create").hasRole("ADMIN")
                .antMatchers("/vitalsync/profesional/create").permitAll()
                .antMatchers("/vitalsync/paciente/*").permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/vitalsync/usuario/pag") // Redirecciona a otra pagina despues de logearse
                .successHandler(authenticationSuccessHandler())
                .permitAll()
                .and()
                .logout() // Configurar la acción de cierre de sesión
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login"); // Redirigir a la página principal después de cerrar sesión

//        http.authorizeRequests().antMatchers("swagger-ui/index.html#").permitAll();
    }
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
//    private static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//        @Override
//        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.sendRedirect("/vitalsync/usuario/pag");
//        }
//    }

    private static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();

//            for (SimpleGrantedAuthority authority : authorities) {
//                if (authority.getAuthority().equals("ROLE_ADMIN")) {
//                    response.sendRedirect("/vitalsync/admin");
//                    return;
//                } else if (authority.getAuthority().equals("ROLE_PROFESIONAL")) {
//                    response.sendRedirect("/vitalsync/usuario/prueba2");
//                    return;
//                } else if (authority.getAuthority().equals("ROLE_PACIENTE")) {
//                    response.sendRedirect("/vitalsync/usuario/prueba3");
//                    return;
//                }
//            }
:
            response.sendRedirect("/vitalsync/usuario/pag");
        }
    }
}