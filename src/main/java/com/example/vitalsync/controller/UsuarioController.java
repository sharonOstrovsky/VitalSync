package com.example.vitalsync.controller;

import com.example.vitalsync.dto.request.UsuarioLoginRequestDTO;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.service.serviceImpl.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> create(@RequestBody UsuarioLoginRequestDTO usuario) throws Exception {
        Usuario result = usuarioService.guardarUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente.");
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() throws Exception{
        List<Usuario> result = usuarioService.listarUsuarios();
        return  ResponseEntity.ok(result);
    }
    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        // Lógica para cerrar sesión aquí
        System.out.println("Se cerro sesion");
        return ResponseEntity.ok("Sesión cerrada exitosamente.");
    }

    @GetMapping("/pag")
    public String prueba (){
        return "Siiii, ingresaste";
    }
    @GetMapping("/prueba2")
    public String prueba2 (){
        return "Sos profesional";
    }

    @GetMapping("/prueba3")
    public String prueba3 (){
        return "Sos PACIENTE";
    }
}
