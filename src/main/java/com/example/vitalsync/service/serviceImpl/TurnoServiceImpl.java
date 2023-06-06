package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.entity.Turno;
import com.example.vitalsync.repository.TurnoRepository;
import com.example.vitalsync.service.service.TurnoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TurnoServiceImpl implements TurnoService {

    private TurnoRepository turnoRepository;
    @Override
    public Turno guardarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }
    @Override
    public List <Turno> guardarTurnos (List<Turno> turnos) {
        return turnoRepository.saveAll(turnos);
    }

}
