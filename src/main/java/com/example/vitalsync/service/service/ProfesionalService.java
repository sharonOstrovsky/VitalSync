package com.example.vitalsync.service.service;
import com.example.vitalsync.dto.request.profesional.ProfesionalComentariosRequestDTO;
import com.example.vitalsync.dto.request.profesional.ProfesionalPuntuacionRequestDTO;
import com.example.vitalsync.dto.request.profesional.ProfesionalRequestDTO;
import com.example.vitalsync.dto.request.profesional.ProfesionalUpdateRequestDTO;
import com.example.vitalsync.dto.response.profesional.*;
import com.example.vitalsync.entity.Profesional;
import com.example.vitalsync.entity.Turno;

import java.util.List;

public interface ProfesionalService {
    List<Profesional> listarProfesionales() throws Exception;
    public ProfesionalResponseDTO guardarProfesional(ProfesionalRequestDTO profesionalReqDto) throws Exception;
    Profesional obtenerProfesionalPorId(Long id) throws Exception;
    public void actualizarEstadoProfesional(Long id) throws Exception;
    public List<ProfesionalPorEspecialidadResponseDTO> obtenerProfesionalesPorEspecialidad(String especialidad) throws Exception;

    List<ProfesionalPorEspecialidadResponseDTO> obtenerProfesionalesOrdenadosPorPuntuacion() throws Exception;
    List<ProfesionalPorEspecialidadResponseDTO> obtenerProfesionalesOrdenadosPorHonorario() throws Exception;
    public void eliminarProfesional(Long id) throws Exception;
    public ProfesionalUpdateResponseDTO editarProfesional (Long id, ProfesionalUpdateRequestDTO profesional) throws Exception;

    public Profesional traerProfesionalPorUsuario(String email) throws Exception;

    public Profesional guardarComentario(ProfesionalComentariosRequestDTO profesionalComentariosRequestDTO) throws Exception;

    ProfesionalPedirComentariosResponseDTO listarComentarios(Long id) throws Exception;

    void eliminarComentario(ProfesionalComentariosRequestDTO profesionalComentariosRequestDTO) throws Exception;

    ProfesionalPuntuacionResponseDTO puntuarProfesional(Long id, ProfesionalPuntuacionRequestDTO profesionalPuntuacionRequestDTO) throws Exception;

    ProfesionalPuntuacionResponseDTO obtenerPuntuacion(Long id)throws Exception;

    public List<Turno> mostrarTurnos(Long id) throws Exception;
}
