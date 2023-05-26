package com.example.vitalsync.service.service;

import com.example.vitalsync.dto.request.paciente.PacienteRequestDTO;
import com.example.vitalsync.dto.response.PacienteResponseDTO;
import com.example.vitalsync.entity.Paciente;

import java.util.List;

public interface PacienteService {

    List<Paciente> listarPacientes() throws Exception;


    Paciente obtenerPacientePorId(Long id) throws Exception;

    Paciente actualizarPersonal(Paciente paciente) throws Exception;

    void eliminarPaciente(Long id) throws Exception;

    public PacienteResponseDTO guardarPaciente(PacienteRequestDTO pacienteDto) throws Exception;

}
