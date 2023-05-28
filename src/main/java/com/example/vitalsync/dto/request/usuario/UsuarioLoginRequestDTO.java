package com.example.vitalsync.dto.request.usuario;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Data
@Getter
@Setter
@ToString
public class UsuarioLoginRequestDTO {
    private String email;
    private String clave;
}
