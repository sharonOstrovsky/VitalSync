package com.example.vitalsync.dto.response.admin;

import com.example.vitalsync.dto.request.usuario.UsuarioLoginRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponseDTO {
    private UsuarioLoginRequestDTO usuario;
}
