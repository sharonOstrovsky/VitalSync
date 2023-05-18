package com.example.vitalsync.controller;

import com.example.vitalsync.dto.request.UsuarioRequestDTO;
import com.example.vitalsync.dto.response.UsuarioResponseDTO;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.service.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/vitalsync/usuario")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService usuarioService;
    @PostMapping("/create")
    //TODO public ResponseEntity<ProfesionalResponseDTO>
    public ResponseEntity<UsuarioResponseDTO> create(@RequestBody UsuarioRequestDTO usuarioRequestDto) throws Exception {
        UsuarioResponseDTO result = usuarioService.guardarUsuario(usuarioRequestDto);

        return ResponseEntity.ok(result);
    }




    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() throws Exception{
        List<Usuario> result = usuarioService.listarUsuarios();
        return  ResponseEntity.ok(result);
    }

}
