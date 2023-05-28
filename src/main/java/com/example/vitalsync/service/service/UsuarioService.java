package com.example.vitalsync.service.service;

import com.example.vitalsync.dto.request.usuario.UsuarioLoginRequestDTO;
import com.example.vitalsync.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listarUsuarios() throws Exception;
    Usuario guardarUsuario(UsuarioLoginRequestDTO usuarioDto) throws Exception;

}
