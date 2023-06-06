package com.example.vitalsync.dto.response.historial;

import com.example.vitalsync.entity.HistorialMedico;
import com.example.vitalsync.entity.Turno;
import lombok.Data;

@Data
public class HistorialResponseDTO {
    private Long id;
    private Turno turno;
    private HistorialMedico historialMedico;
}
