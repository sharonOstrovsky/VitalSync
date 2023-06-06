package com.example.vitalsync.service.service;

import com.example.vitalsync.entity.Turno;

import java.util.List;

public interface TurnoService {
    public Turno guardarTurno(Turno turno);
    public List<Turno> guardarTurnos (List<Turno> turnos);

}
