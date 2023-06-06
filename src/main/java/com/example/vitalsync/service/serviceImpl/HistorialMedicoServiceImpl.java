package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.entity.HistorialMedico;
import com.example.vitalsync.repository.HistorialMedicoRepository;
import com.example.vitalsync.service.service.HistorialMedicoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HistorialMedicoServiceImpl implements HistorialMedicoService {

   private HistorialMedicoRepository historialMedicoRepository;

    @Override
    public void actualizarHistorialMedico(HistorialMedico historialMedico) {
        historialMedicoRepository.save(historialMedico);
    }
}
