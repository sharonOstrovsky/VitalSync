package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.dto.request.UsuarioLoginRequestDTO;
import com.example.vitalsync.entity.LoginMessage;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.repository.UsuarioRepository;
import com.example.vitalsync.service.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService,UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() throws Exception {
        return usuarioRepository.findAll();
    }

    @Override
    public LoginMessage loginUsuario(UsuarioLoginRequestDTO usuarioDTO) {
        return null;
    }
    @Override
    public Usuario guardarUsuario(UsuarioLoginRequestDTO usuarioDto) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setClave(usuarioDto.getClave());
        System.out.println("ENTRO");
        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(usuario.getRol().name())
        );

        return new User(usuario.getEmail(), usuario.getClave(), authorities);
    }

}