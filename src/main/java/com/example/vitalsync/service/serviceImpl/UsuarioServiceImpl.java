package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.repository.UsuarioRepository;
import com.example.vitalsync.service.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;


    @Override
    public List<Usuario> listarUsuarios() throws Exception {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuarioDto) throws Exception {
        return usuarioRepository.save(usuarioDto);
    }
}
