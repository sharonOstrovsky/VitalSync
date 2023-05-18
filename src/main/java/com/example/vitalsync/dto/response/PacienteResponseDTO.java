package com.example.vitalsync.dto.response;

import com.example.vitalsync.entity.Turno;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.utils.CoberturaMedica;
import lombok.Data;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
public class PacienteResponseDTO {

    protected String nombre;
    protected String apellido;
    protected String telefono;
    private int edad;
    private String foto;
    private CoberturaMedica coberturaMedica;
}
