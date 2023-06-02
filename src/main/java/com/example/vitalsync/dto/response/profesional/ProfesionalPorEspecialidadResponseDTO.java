package com.example.vitalsync.dto.response.profesional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesionalPorEspecialidadResponseDTO {
    private String nombre;
    private String apellido;

    public ProfesionalPorEspecialidadResponseDTO(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
