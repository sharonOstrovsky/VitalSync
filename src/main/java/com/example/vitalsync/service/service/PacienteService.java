package com.example.vitalsync.service.service;

import com.example.vitalsync.dto.request.historial.HistorialRequestDTO;
import com.example.vitalsync.dto.request.paciente.PacienteRequestDTO;
import com.example.vitalsync.dto.request.paciente.PacienteUpdateRequestDTO;
import com.example.vitalsync.dto.response.paciente.PacienteResponseCompletoDTO;
import com.example.vitalsync.dto.response.paciente.PacienteResponseDTO;
import com.example.vitalsync.entity.HistorialMedico;
import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.entity.Turno;

import java.util.List;

public interface PacienteService {

    List<PacienteResponseCompletoDTO> listarPacientes() throws Exception;

    PacienteResponseDTO obtenerPacientePorId(Long id) throws Exception;

    public PacienteResponseCompletoDTO editarPaciente(Long Id, PacienteUpdateRequestDTO paciente) throws Exception;

    public void cambiarEstadoPaciente(Long id) throws Exception;

    public void eliminarPaciente(Long id) throws Exception;

    public PacienteResponseDTO guardarPaciente(PacienteRequestDTO pacienteDto) throws Exception;

    public Paciente traerPacientePorUsuario(String email) throws Exception;

    public List<Turno> verTurnoProfesional(Long id) throws Exception;

    public void reservarTurno(Long id_medico, Long id_turno, Long id_paciente) throws Exception;

    public Paciente actualizarPaciente(Paciente paciente) throws Exception;
    public List<HistorialMedico> retornarHistorialPorId(Long Id) throws Exception;
    public void agregarAlHistorial(Long id, HistorialRequestDTO historial) throws Exception;
}
