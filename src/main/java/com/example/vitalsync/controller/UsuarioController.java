package com.example.vitalsync.controller;

import com.example.vitalsync.dto.request.UsuarioLoginRequestDTO;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.service.serviceImpl.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vitalsync/usuario")
@CrossOrigin
@AllArgsConstructor
public class UsuarioController {

    private UsuarioServiceImpl usuarioService;

    @PostMapping("/create")
    //TODO public ResponseEntity<ProfesionalResponseDTO>
    public ResponseEntity<Usuario> create(@RequestBody UsuarioLoginRequestDTO usuario) throws Exception {
        Usuario result = usuarioService.guardarUsuario(usuario);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() throws Exception{
        List<Usuario> result = usuarioService.listarUsuarios();
        return  ResponseEntity.ok(result);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody UsuarioLoginRequestDTO usuarioDTO){
//        LoginMessage l = usuarioService.loginUsuario(usuarioDTO);
//        return ResponseEntity.ok(l);
//    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginMessage> loginUser(@RequestBody UsuarioLoginRequestDTO usuarioDTO) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        authentication = usuarioService.authenticate(authentication);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        if (authentication.isAuthenticated()) {
//            // Si el usuario ya está autenticado, retorna un mensaje indicando que ya ha iniciado sesión
//            return ResponseEntity.ok(new LoginMessage("Ya has iniciado sesióndsd", true));
//        }
//
//        try {
//            // Intenta autenticar al usuario
//            authentication = new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getClave());
//            authentication = usuarioService.authenticate(authentication);
//
//            // Establece la autenticación en el contexto de seguridad
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            // Retorna un mensaje indicando que el inicio de sesión fue exitoso
//            return ResponseEntity.ok(new LoginMessage("Inicio de sesión exitoso", true));
//        } catch (AuthenticationException e) {
//            // Retorna un mensaje indicando que el inicio de sesión falló
//            return ResponseEntity.ok(new LoginMessage("Inicio de sesión fallido", false));
//        }
//    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginMessage> loginUser(@RequestBody UsuarioLoginRequestDTO usuarioDTO) {
//        try {
//            Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getClave());
//            authentication = usuarioService.authenticate(authentication);
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            if (authentication.isAuthenticated()) {
//                return ResponseEntity.ok(new LoginMessage("Ya has iniciado sesión", true));
//            } else {
//                return ResponseEntity.ok(new LoginMessage("Inicio de sesión fallido", false));
//            }
//        } catch (AuthenticationException e) {
//            return ResponseEntity.ok(new LoginMessage("Inicio de sesión fallido", false));
//        }
//    }

}
