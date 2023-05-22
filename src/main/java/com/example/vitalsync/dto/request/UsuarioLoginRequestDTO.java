package com.example.vitalsync.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLoginRequestDTO {
    private String email;
    private String clave;
}
