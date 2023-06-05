package com.example.vitalsync.dto.request.profesional;

import com.example.vitalsync.dto.request.usuario.UsuarioLoginRequestDTO;
import com.example.vitalsync.entity.Turno;

import java.util.List;

public class ProfesionalRequestCompletoDTO {
    private String nombre;
    private String apellido;
    private String especialidad;
    private UsuarioLoginRequestDTO usuario;
    private List<Turno> diasTrabajo;
    private Float honorario;
}
