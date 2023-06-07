package com.example.vitalsync.dto.response.profesional;

import com.example.vitalsync.entity.Turno;
import com.example.vitalsync.entity.Usuario;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.List;

@Data
public class ProfesionalResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private Boolean estado;
    @OneToMany
    private List<Turno> turnos;
    private Usuario usuario;
}
