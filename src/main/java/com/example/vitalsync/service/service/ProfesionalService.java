package com.example.vitalsync.service.service;
import com.example.vitalsync.dto.request.profesional.ProfesionalRequestDTO;
import com.example.vitalsync.dto.response.ProfesionalPorEspecialidadResponseDTO;
import com.example.vitalsync.dto.response.ProfesionalResponseDTO;
import com.example.vitalsync.entity.Profesional;

import java.util.List;

public interface ProfesionalService {
    List<Profesional> listarProfesionales() throws Exception;
    public ProfesionalResponseDTO guardarProfesional(ProfesionalRequestDTO profesionalReqDto) throws Exception;
    Profesional obtenerProfesionalPorId(Long id) throws Exception;
    Profesional actualizarProfesional(Profesional profesional)throws Exception;
    void eliminarProfesional(Long id) throws Exception;
    public List<ProfesionalPorEspecialidadResponseDTO> obtenerProfesionalesPorEspecialidad(String especialidad) throws Exception;

}
