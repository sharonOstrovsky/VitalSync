package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.repository.UsuarioRepository;
import com.example.vitalsync.service.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> listarUsuarios() throws Exception {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuarioDto) throws Exception {
        Usuario usuario = new Usuario(
                usuarioDto.getId(),
                usuarioDto.getUsuario(),
                this.passwordEncoder.encode(usuarioDto.getClave()),
                usuarioDto.getRol()
        );
       return usuarioRepository.save(usuario);
    }

}
