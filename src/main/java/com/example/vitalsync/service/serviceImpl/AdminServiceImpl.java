package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.dto.request.UsuarioLoginRequestDTO;
import com.example.vitalsync.entity.Admin;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.service.service.AdminService;
import com.example.vitalsync.utils.Rol;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private final ModelMapper modelMapper = new ModelMapper();
    private UsuarioServiceImpl usuarioService;
    private PasswordEncoder passwordEncoder;
    @Override
    public List<Admin> listarAdmins() throws Exception {
        return null;
    }

    @Override
    public Admin guardarAdmin(Admin admin) throws Exception {
        Usuario usuario = modelMapper.map(admin.getUsuario(), Usuario.class);
        usuario.setClave(passwordEncoder.encode(admin.getUsuario().getClave()));
        UsuarioLoginRequestDTO usuarioDto = new UsuarioLoginRequestDTO();
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setClave(usuario.getClave());
        Usuario usuarioGuardado= usuarioService.guardarUsuario(usuarioDto);
        usuarioGuardado.setRol(Rol.PACIENTE);
        return null;
    }

    @Override
    public Admin obtenerAdminPorId(Long id) throws Exception {
        return null;
    }

    @Override
    public Admin actualizarAdmin(Admin admin) throws Exception {
        return null;
    }

    @Override
    public void eliminarAdmin(Long id) throws Exception {

    }
}
