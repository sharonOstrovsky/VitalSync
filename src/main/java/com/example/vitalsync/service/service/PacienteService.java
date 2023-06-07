package com.example.vitalsync.service.service;

import com.example.vitalsync.dto.request.paciente.PacienteRequestDTO;
import com.example.vitalsync.dto.request.paciente.PacienteUpdateRequestDTO;
import com.example.vitalsync.dto.response.paciente.PacienteResponseCompletoDTO;
import com.example.vitalsync.dto.response.paciente.PacienteResponseDTO;
import com.example.vitalsync.entity.Paciente;

import java.util.List;

public interface PacienteService {

    List<PacienteResponseCompletoDTO> listarPacientes() throws Exception;

    PacienteResponseDTO obtenerPacientePorId(Long id) throws Exception;

    public PacienteResponseCompletoDTO editarPaciente(Long Id, PacienteUpdateRequestDTO paciente) throws Exception;

    public void cambiarEstadoPaciente (Long id) throws Exception;
    public void eliminarPaciente (Long id) throws Exception;

    public PacienteResponseDTO guardarPaciente(PacienteRequestDTO pacienteDto) throws Exception;

    public Paciente traerPacientePorUsuario (String email) throws Exception;

}
