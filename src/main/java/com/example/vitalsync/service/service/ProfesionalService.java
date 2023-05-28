package com.example.vitalsync.service.service;
import com.example.vitalsync.dto.request.profesional.ProfesionalRequestDTO;
import com.example.vitalsync.dto.response.profesional.ProfesionalPorEspecialidadResponseDTO;
import com.example.vitalsync.dto.response.profesional.ProfesionalResponseDTO;
import com.example.vitalsync.entity.Profesional;

import java.util.List;

public interface ProfesionalService {
    List<Profesional> listarProfesionales() throws Exception;
    public ProfesionalResponseDTO guardarProfesional(ProfesionalRequestDTO profesionalReqDto) throws Exception;
    Profesional obtenerProfesionalPorId(Long id) throws Exception;
    Profesional actualizarProfesional(Profesional profesional)throws Exception;
    public void actualizarEstadoProfesional(Long id) throws Exception;
    public List<ProfesionalPorEspecialidadResponseDTO> obtenerProfesionalesPorEspecialidad(String especialidad) throws Exception;

    public void eliminarProfesional(Long id) throws Exception;
}
