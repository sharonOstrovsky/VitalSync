package com.example.vitalsync.services;

import com.example.vitalsync.entity.Paciente;

import java.util.List;

public interface PacienteService {

    List<Paciente> listarPacientes();
    Paciente guardarPaciente(Paciente pacienteDto);
    Paciente obtenerPacientePorId(Long id);
    Paciente actualizarPersonal(Paciente paciente);
    void eliminarPaciente(Long id);

}
