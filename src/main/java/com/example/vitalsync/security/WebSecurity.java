package com.example.vitalsync.security;
import com.example.vitalsync.service.serviceImpl.UsuarioServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableWebSecurity
//@AllArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioServiceImpl();
    }

    /**
     El metodo AuthenticationManagerBuilder es parte de Spring Security y es usado para configurar el authentication
     manager.

     El metodo .inMemoryAuthentication() configura la autenticacion para usar en memoria del user details.
     *  auth.inMemoryAuthentication() -> Significa que las credenciales del usuario son almacenadas en memoria y no persisten en una base de datos.
     *  .withUser("Max") -> Setea el username del usuario en la memoria.
     *  .password(passwordEncoder().encode("1234")) -> Setea la contraseña del usuario en memoria. passwordEncoder().encode("1234")
     indica que la contraseña debe ser codificada con un codificador de contraseñas (password encoder)
     *  .authorities("ROL_ADMIN") -> Setea los roles para el usuario en memoria, en este caso se le asigan el rol ADMIN

     *  auth.userDetailsService(userDetailsService()) -> Configura el authentication manager para usar un userDetailsService
     personalizado.

     *  userDetailsService() Debe retornar una implementación de la interface UserDetailService, el cual proporciona un user
     details (username, password, authorities) para la autenticación.
     *  .passwordEncoder(passwordEncoder()) -> Configura  el codificador de contraseñas que sera usado para codificar y verificar
     contraseñas. passwordEncoder() debe retornar una instancia del passwrodEncoder, como BCryptPasswordEncoder para el
     almacenamiento seguro de contraseñas.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Max")
                .password(passwordEncoder().encode("1234"))
                .authorities("ADMIN");
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    /**
     *  Este método sobreescribe el método authenticationManagerBean() de la clase WebSecurityConfigurerAdapter.
     *  Se utiliza para exponer el Bean AuthenticationManager, que es responsable de autenticar las credenciales del usuario.
     * Al anular y exponer el método authenticationManagerBean() como un Bean, se permite que otros componentes de tu
     * aplicación accedan y utilicen el Bean AuthenticationManager
     *
     * @return Devuelve el bean AuthenticationManager
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable()
                .authorizeRequests()
//                  .antMatchers("/vitalsync/usuario/*").permitAll()
                .antMatchers("/vitalsync/auth/login").permitAll()
                .antMatchers("/vitalsync/usuario/prueba2").hasRole("PROFESIONAL")
                .antMatchers("/vitalsync/usuario/prueba3").hasRole("PACIENTE")
                //.antMatchers("/vitalsync/usuario/pag").permitAll()
                .antMatchers("/vitalsync/admin/*").permitAll()
                .antMatchers("/vitalsync/profesional/create").hasRole("ADMIN")
                //.antMatchers("/vitalsync/profesional/create").permitAll()
                .antMatchers("/vitalsync/paciente/*").permitAll()
                .and()
                .formLogin()
                .loginPage("/vitalsync/auth/login")
                .loginProcessingUrl("/vitalsync/usuario/page") // Redirecciona a otra pagina despues de logearse
                .successHandler(authenticationSuccessHandler())
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .httpBasic()
                .and()
                .logout() // Configurar la acción de cierre de sesión
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID","SESSIONID")
                .logoutSuccessUrl("/login"); // Redirigir a la página principal después de cerrar sesión

        //http.authorizeRequests().antMatchers("swagger-ui/index.html#").permitAll();
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

            for (SimpleGrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    response.sendRedirect("https://www.google.com.ar/");
                    return;
                } else if (authority.getAuthority().equals("ROLE_PROFESIONAL")) {
                    response.sendRedirect("/vitalsync/usuario/prueba2");
                    return;
                } else if (authority.getAuthority().equals("ROLE_PACIENTE")) {
                    response.sendRedirect("/vitalsync/usuario/prueba3");
                    return;
                }
            }
            response.sendRedirect("/vitalsync/usuario/pag");
        }
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // Reemplaza con el origen correcto de tu aplicación React
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
