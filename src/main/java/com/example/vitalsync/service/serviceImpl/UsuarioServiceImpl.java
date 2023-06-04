package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.dto.request.usuario.UsuarioLoginRequestDTO;

import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.repository.UsuarioRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service
//@AllArgsConstructor
//@NoArgsConstructor
@Primary
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        System.out.println(usuario + "!!!!!");
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + usuario);
        }
        List<GrantedAuthority> permissions = new ArrayList<>();
        GrantedAuthority p = new SimpleGrantedAuthority(usuario.getRol().toString());
        permissions.add(p);
        User u = new User(usuario.getEmail(), usuario.getClave(), permissions);

        System.out.println(u);
        return u;
    }
    public List<Usuario> listarUsuarios() throws Exception {
        return usuarioRepository.findAll();
    }
//    @Override
    public Usuario guardarUsuario(UsuarioLoginRequestDTO usuarioDto) throws Exception {
        Usuario usuario = modelMapper.map(usuarioDto,Usuario.class);

        return usuarioRepository.save(usuario);
    }



}