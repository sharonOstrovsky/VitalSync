package com.example.vitalsync.dto.request.profesional;

import com.example.vitalsync.dto.request.UsuarioLoginRequestDTO;
import lombok.Data;

@Data
public class ProfesionalRequestDTO {
    private String nombre;
    private String apellido;
    private String especialidad;
    private UsuarioLoginRequestDTO usuario;
}
