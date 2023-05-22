package com.example.vitalsync.dto.request;

import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.utils.CoberturaMedica;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class PacienteRequestDTO {
    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;
    private int edad;
    private String foto;
    @Enumerated(EnumType.STRING)
    private CoberturaMedica coberturaMedica;
    private Usuario usuario;
}
