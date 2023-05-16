package com.example.vitalsync.entity;

import com.example.vitalsync.utils.CoberturaMedica;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Paciente")
public class Paciente extends Persona {
    private int edad;
    private String foto;
    @OneToMany
    private List<Turno> turnos;
    private CoberturaMedica coberturaMedica;
    @OneToOne
    protected Usuario usuario;
    // ? private

}
