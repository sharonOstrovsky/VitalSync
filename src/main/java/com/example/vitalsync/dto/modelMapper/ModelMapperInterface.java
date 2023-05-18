package com.example.vitalsync.dto.modelMapper;

import com.example.vitalsync.dto.request.PacienteRequestDTO;
import com.example.vitalsync.dto.request.UsuarioRequestDTO;
import com.example.vitalsync.dto.response.PacienteResponseDTO;
import com.example.vitalsync.dto.response.UsuarioResponseDTO;
import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.entity.Usuario;

public interface ModelMapperInterface {

    Paciente pacienteReqDtoToPaciente(PacienteRequestDTO pacienteRequestDTO);

    PacienteResponseDTO pacienteToPacienteResponseDTO(Paciente paciente);

    Usuario usuarioReqDtoToUsuario(UsuarioRequestDTO usuarioRequestDTO);

    UsuarioResponseDTO usuarioToUsuarioResponseDTO(Usuario usuario);
}
