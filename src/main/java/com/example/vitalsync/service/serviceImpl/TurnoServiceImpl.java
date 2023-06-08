package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.entity.Turno;
import com.example.vitalsync.repository.TurnoRepository;
import com.example.vitalsync.service.service.TurnoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TurnoServiceImpl implements TurnoService {

    private TurnoRepository turnoRepository;

    @Override
    public Turno guardarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public Turno guardarTurnoPorId(Long id) {
        Optional<Turno> turno = buscarTurnoPorId(id);
        return turnoRepository.save(turno.get());
    }

    @Override
    public List<Turno> guardarTurnos(List<Turno> turnos) {
        return turnoRepository.saveAll(turnos);
    }

    public Optional<Turno> buscarTurnoPorId(Long id) {
        return turnoRepository.findById(id);
    }
}
