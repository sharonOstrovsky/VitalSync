package com.example.vitalsync.services;

import com.example.vitalsync.entity.Profesional;
import com.example.vitalsync.repositories.ProfesionalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfesionalServiceImp implements ProfesionalService {
    private final ProfesionalRepository profesionalRepository;


    @Override
    public List<Profesional> listarProfesional() {
        return profesionalRepository.findAll();
    }

    @Override
    public Profesional guardarProfesional(Profesional profesionalDto) {
        return profesionalRepository.save(profesionalDto);
    }

    @Override
    public Profesional obtenerProfesionalPorId(Long id) {
        return profesionalRepository.findById(id).get();
    }

    @Override
    public Profesional actualizarProfesional(Profesional profesional) {
        return profesionalRepository.save(profesional);
    }

    @Override
    public void eliminarProfesional(Long id) {
        profesionalRepository.deleteById(id);
    }
}
