package com.example.vitalsync.entity;

import com.example.vitalsync.utility.CoberturaMedica;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Paciente")
public class Paciente extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_paciente;
    private int edad;
    private String foto;
    @OneToMany
    private List<Turno> turnos;
    private CoberturaMedica coberturaMedica;
    @OneToOne
    protected Usuario usuario;

}
