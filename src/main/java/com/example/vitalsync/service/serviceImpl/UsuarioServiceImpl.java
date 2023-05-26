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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService,UserDetailsService {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + usuario);
        }
        List<GrantedAuthority> permissions = new ArrayList<>();
        GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
        permissions.add(p);

        return new User(usuario.getEmail(), usuario.getClave(), permissions);
    }
    public List<Usuario> listarUsuarios() throws Exception {
        return usuarioRepository.findAll();
    }
    @Override
    public Usuario guardarUsuario(UsuarioLoginRequestDTO usuarioDto) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setClave(passwordEncoder.encode(usuarioDto.getClave()));
        return usuarioRepository.save(usuario);
    }



}