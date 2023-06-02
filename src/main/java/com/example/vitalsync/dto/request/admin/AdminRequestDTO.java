package com.example.vitalsync.dto.request.admin;

import com.example.vitalsync.dto.request.usuario.UsuarioLoginRequestDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminRequestDTO {
    private UsuarioLoginRequestDTO usuario;
}
