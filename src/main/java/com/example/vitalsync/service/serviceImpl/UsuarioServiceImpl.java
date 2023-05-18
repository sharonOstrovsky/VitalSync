package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.dto.modelMapper.ModelMapperInterface;
import com.example.vitalsync.dto.request.UsuarioRequestDTO;
import com.example.vitalsync.dto.response.UsuarioResponseDTO;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.repository.UsuarioRepository;
import com.example.vitalsync.service.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapperInterface modelMapperInterface;

    @Override
    public List<Usuario> listarUsuarios() throws Exception {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioResponseDTO guardarUsuario(UsuarioRequestDTO usuarioRequestDto) throws Exception {
        Usuario usuario = modelMapperInterface.usuarioReqDtoToUsuario(usuarioRequestDto);
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return modelMapperInterface.usuarioToUsuarioResponseDTO(usuarioGuardado);
    }


}
