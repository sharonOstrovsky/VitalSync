package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.repository.PacienteRepository;
import com.example.vitalsync.service.service.PacienteService;

import java.util.List;

public class PacienteServiceImpl implements PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<Paciente> listarPacientes() throws Exception {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente guardarPaciente(Paciente pacienteDto) throws Exception {
        return pacienteRepository.save(pacienteDto);
    }

    @Override
    public Paciente obtenerPacientePorId(Long id) throws Exception {
        return pacienteRepository.findById(id).get();
    }

    @Override
    public Paciente actualizarPersonal(Paciente paciente) throws Exception {
        return pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Long id) throws Exception {
        pacienteRepository.deleteById(id);
    }
}
