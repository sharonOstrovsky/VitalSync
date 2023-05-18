package com.example.vitalsync.dto.modelMapper;

import com.example.vitalsync.dto.request.PacienteRequestDTO;
import com.example.vitalsync.dto.request.UsuarioRequestDTO;
import com.example.vitalsync.dto.response.PacienteResponseDTO;
import com.example.vitalsync.dto.response.UsuarioResponseDTO;
import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.entity.Persona;
import com.example.vitalsync.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperImpl implements ModelMapperInterface {
    private final ModelMapper modelMapper = new ModelMapper();

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public Paciente pacienteReqDtoToPaciente(PacienteRequestDTO pacienteRequestDTO) {
        return modelMapper.map(pacienteRequestDTO, Paciente.class);
    }


    @Override
    public PacienteResponseDTO pacienteToPacienteResponseDTO(Paciente paciente) {
        return modelMapper.map(paciente, PacienteResponseDTO.class);
    }

    @Override
    public Usuario usuarioReqDtoToUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        return modelMapper.map(usuarioRequestDTO, Usuario.class);
    }

    @Override
    public UsuarioResponseDTO usuarioToUsuarioResponseDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

}
