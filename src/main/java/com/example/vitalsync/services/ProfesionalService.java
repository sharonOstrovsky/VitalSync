package com.example.vitalsync.services;

import com.example.vitalsync.entity.Profesional;

import java.util.List;

public interface ProfesionalService {
    List<Profesional> listarProfesional();
    Profesional guardarProfesional(Profesional profesionalDto);
    Profesional obtenerProfesionalPorId(Long id);
    Profesional actualizarProfesional(Profesional profesional);
    void eliminarProfesional(Long id);
}
