package com.example.vitalsync.dto.request.paciente;

import com.example.vitalsync.dto.request.usuario.UsuarioLoginRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteRequestDTO {
    private String nombre;
    private String apellido;
    private UsuarioLoginRequestDTO usuario;
}
