package com.example.vitalsync.dto.response.profesional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesionalPorEspecialidadResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;

    public ProfesionalPorEspecialidadResponseDTO(Long id,String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
