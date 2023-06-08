package com.example.vitalsync.service.service;

import com.example.vitalsync.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface TurnoService {
    public Turno guardarTurno(Turno turno);
    public List<Turno> guardarTurnos (List<Turno> turnos);
    public Optional<Turno> buscarTurnoPorId (Long id);
    public Turno guardarTurnoPorId(Long id);
}
