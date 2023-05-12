package com.example.vitalsync.services;

import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.repositories.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PacienteServiceImp implements PacienteService {
    private final PacienteRepository pacienteRepository;

    /*
    public PacienteServiceImp(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

     */

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente guardarPaciente(Paciente pacienteDto) {
        return pacienteRepository.save(pacienteDto);
    }

    @Override
    public Paciente obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id).get();
    }

    @Override
    public Paciente actualizarPersonal(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}
