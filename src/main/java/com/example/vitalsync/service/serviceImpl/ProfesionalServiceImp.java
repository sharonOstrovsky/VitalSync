package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.entity.Profesional;
import com.example.vitalsync.repository.ProfesionalRepository;
import com.example.vitalsync.service.service.ProfesionalService;


import java.util.List;

public class ProfesionalServiceImp implements ProfesionalService {
    private final ProfesionalRepository profesionalRepository;

    public ProfesionalServiceImp(ProfesionalRepository profesionalRepository) {
        this.profesionalRepository = profesionalRepository;
    }


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
