package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.dto.request.admin.AdminRequestDTO;
import com.example.vitalsync.dto.request.usuario.UsuarioLoginRequestDTO;
import com.example.vitalsync.dto.response.admin.AdminResponseDTO;
import com.example.vitalsync.entity.Admin;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.repository.AdminRepository;
import com.example.vitalsync.service.service.AdminService;
import com.example.vitalsync.utils.Rol;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private UsuarioServiceImpl usuarioService;
    private PasswordEncoder passwordEncoder;
    private AdminRepository adminRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<Admin> listarAdmins() throws Exception {
        return null;
    }

    @Override
    public AdminResponseDTO guardarAdmin(AdminRequestDTO admin) throws Exception {
        Usuario usuario = modelMapper.map(admin.getUsuario(), Usuario.class);
        usuario.setClave(passwordEncoder.encode(admin.getUsuario().getClave()));
        UsuarioLoginRequestDTO usuarioDto = new UsuarioLoginRequestDTO();
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setClave(usuario.getClave());
        usuario= usuarioService.guardarUsuario(usuarioDto);
        usuario.setRol(Rol.ADMIN);

        Admin ad = new Admin();
        ad.setUsuario(usuario);
        adminRepository.save(ad);
        return modelMapper.map(ad, AdminResponseDTO.class);
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
