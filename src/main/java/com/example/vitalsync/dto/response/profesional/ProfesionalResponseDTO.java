package com.example.vitalsync.dto.response.profesional;

import lombok.Data;

@Data
public class ProfesionalResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private Boolean estado;
}
