package com.example.vitalsync.controller;

import com.example.vitalsync.dto.request.usuario.UsuarioLoginRequestDTO;
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
    public ResponseEntity<String> create(@RequestBody UsuarioLoginRequestDTO usuario) throws Exception {
        Usuario result = usuarioService.guardarUsuario(usuario);
        if(result == null){
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario ya existente.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente.");
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() throws Exception{
        List<Usuario> result = usuarioService.listarUsuarios();

        return  ResponseEntity.ok(result);
    }
}
