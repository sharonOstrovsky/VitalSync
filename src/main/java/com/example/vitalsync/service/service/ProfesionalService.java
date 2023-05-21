package com.example.vitalsync.service.service;
import com.example.vitalsync.entity.Profesional;

import java.util.List;

public interface ProfesionalService {
    List<Profesional> listarProfesionales() throws Exception;
    Profesional guardarProfesional (Profesional profesionalReqDto) throws Exception;
    Profesional obtenerProfesionalPorId(Long id) throws Exception;
    Profesional actualizarProfesional(Profesional profesional)throws Exception;
    void eliminarProfesional(Long id) throws Exception;
    List<Profesional> obtenerProfesionalesPorEspecialidad (String especialidad) throws Exception;
}
