package com.example.vitalsync.entity;

import com.example.vitalsync.utils.CoberturaMedica;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Paciente")
public class Paciente extends Persona {
    private int edad;
    private String foto;
    @OneToMany
    private List<Turno> turnos;
    @Enumerated (EnumType.STRING)
    private CoberturaMedica coberturaMedica;
    @OneToOne
    protected Usuario usuario;



}
