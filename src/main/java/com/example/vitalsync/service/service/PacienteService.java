package com.example.vitalsync.service.service;

import com.example.vitalsync.entity.Paciente;

import java.util.List;

public interface PacienteService {

    List<Paciente> listarPacientes() throws Exception;

    Paciente guardarPaciente(Paciente pacienteDto) throws Exception;

    Paciente obtenerPacientePorId(Long id) throws Exception;

    Paciente actualizarPersonal(Paciente paciente) throws Exception;

    void eliminarPaciente(Long id) throws Exception;

}