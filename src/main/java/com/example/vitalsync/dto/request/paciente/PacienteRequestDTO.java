package com.example.vitalsync.dto.request.paciente;

import com.example.vitalsync.dto.request.UsuarioLoginRequestDTO;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.utils.CoberturaMedica;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteRequestDTO {
    private String nombre;
    private String apellido;
    private UsuarioLoginRequestDTO usuario;
}
