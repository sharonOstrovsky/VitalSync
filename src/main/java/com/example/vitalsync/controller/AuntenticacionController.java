package com.example.vitalsync.controller;

import com.example.vitalsync.dto.request.usuario.UsuarioInfoRequestDTO;
import com.example.vitalsync.dto.request.usuario.UsuarioLoginRequestDTO;
import com.example.vitalsync.dto.response.paciente.PacienteResponseCompletoDTO;
import com.example.vitalsync.dto.response.paciente.PacienteResponseDTO;
import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.service.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/vitalsync/auth")
@CrossOrigin
public class AuntenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private PacienteService pacienteService;
    private PasswordEncoder passwordEncoder;

    public AuntenticacionController(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response, @RequestBody UsuarioLoginRequestDTO authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {
        try {
            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(), passwordEncoder.encode(authenticationRequest.getClave())));
            System.out.println("paso");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();

            HttpSession session = request.getSession();
            session.setAttribute("email", authenticationRequest.getEmail());
            session.setAttribute("clave", authenticationRequest.getClave());

            // Construir el objeto UsuarioInfo con la información deseada
            UsuarioInfoRequestDTO userInfo = new UsuarioInfoRequestDTO();
            userInfo.setFirstName(user.getUsername());
            userInfo.setLastName(user.getPassword());
            userInfo.setRoles(user.getAuthorities().toArray());

           // if() Si rol == paciente
//            Paciente p = pacienteService.traerPacientePorUsuario(authenticationRequest.getEmail());
//            PacienteResponseCompletoDTO userInfo = new PacienteResponseCompletoDTO();
//            userInfo.setNombre(p.getNombre());
//            userInfo.setApellido(p.getApellido());
//            userInfo.setTelefono(p.getTelefono());
//            userInfo.setFoto();
//            userInfo.setEdad(p.getEdad());
//            userInfo.setTurnos(p.getTurnos());
//            userInfo.setCoberturaMedica(p.getCoberturaMedica());

            // Agregar la cookie a la respuesta HTTP
            Cookie sessionCookie = new Cookie("SESSIONID", session.getId());
            sessionCookie.setDomain("localhost");
            sessionCookie.setHttpOnly(true);
            sessionCookie.setSecure(true);
            sessionCookie.setMaxAge(3600);
            response.addCookie(sessionCookie);

            // Devolver la información del usuario en la respuesta junto con el estado OK
            return ResponseEntity.ok().body(userInfo);
        } catch (AuthenticationException ex) {
            // El inicio de sesión falló, enviar una respuesta con estado 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // Borrar las cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        // Opcional: Invalidar la sesión
        request.getSession().invalidate();

        return ResponseEntity.ok("Sesión cerrada exitosamente.");
    }

}