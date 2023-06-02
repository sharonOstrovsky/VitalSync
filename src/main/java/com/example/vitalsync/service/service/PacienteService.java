package com.example.vitalsync.service.service;

import com.example.vitalsync.dto.request.paciente.PacienteRequestDTO;
import com.example.vitalsync.dto.response.paciente.PacienteResponseDTO;
import com.example.vitalsync.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    List<Paciente> listarPacientes() throws Exception;

    Paciente obtenerPacientePorId(Long id) throws Exception;
    Optional<Paciente> obtenerPacientePorEmail(String email) throws Exception;

    Paciente actualizarPersonal(Paciente paciente) throws Exception;

    void eliminarPaciente(Long id) throws Exception;

    public PacienteResponseDTO guardarPaciente(PacienteRequestDTO pacienteDto) throws Exception;

}
