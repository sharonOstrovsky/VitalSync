package com.example.vitalsync.dto.response;

import com.example.vitalsync.entity.Turno;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.utils.CoberturaMedica;
import lombok.Data;


import java.util.List;

@Data
public class PacienteResponseDTO {

    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;
    private int edad;
    private String foto;
    private List<Turno> turnos;
    private CoberturaMedica coberturaMedica;
    private Usuario usuario;
}
