package com.example.vitalsync.service.service;

import com.example.vitalsync.dto.request.UsuarioRequestDTO;
import com.example.vitalsync.dto.response.UsuarioResponseDTO;
import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> listarUsuarios() throws Exception;
    UsuarioResponseDTO guardarUsuario(UsuarioRequestDTO usuarioRequestDto) throws Exception;


}
