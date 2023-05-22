package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.dto.request.UsuarioLoginRequestDTO;
import com.example.vitalsync.entity.LoginMessage;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.repository.UsuarioRepository;
import com.example.vitalsync.service.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service

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
                usuarioDto.getEmail(),
                usuarioDto.getRol()
        );
       return usuarioRepository.save(usuario);
    }
    @Override
    public LoginMessage loginUsuario(UsuarioLoginRequestDTO usuarioRequestDTO) {
        String msg = "";
        Usuario u = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail());
        if (u != null) {
            String password = usuarioRequestDTO.getClave();
            String encodedPassword = u.getClave();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Usuario> employee = usuarioRepository.findOneByEmailAndPassword(usuarioRequestDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {

                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("Email not exits", false);
        }


    }
}
