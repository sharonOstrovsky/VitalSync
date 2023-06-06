package com.example.vitalsync.dto.request.historial;

import lombok.Data;

@Data
public class HistorialRequestDTO {
    private Long id;
    private Long turno;
    private String observaciones;
}
