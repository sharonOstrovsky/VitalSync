package com.example.vitalsync.entity;

import com.example.vitalsync.utility.CoberturaMedica;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class Paciente extends Persona {
    private int edad;
    private String foto;
    @OneToMany
    private List<Turno> turnos;
    private CoberturaMedica coberturaMedica;

}
