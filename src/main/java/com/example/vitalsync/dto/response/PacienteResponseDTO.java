package com.example.vitalsync.dto.response;

import com.example.vitalsync.dto.request.UsuarioLoginRequestDTO;
import lombok.Data;

@Data
public class PacienteResponseDTO {
    private String nombre;
    private String apellido;
//    private UsuarioLoginRequestDTO usuario;
}
