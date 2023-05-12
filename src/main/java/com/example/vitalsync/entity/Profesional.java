package com.example.vitalsync.entity;

import com.example.vitalsync.utility.CoberturaMedica;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
@Table(name = "Profesional")
public class Profesional extends Persona{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_profesional;
    private String especialidad;
    private boolean estado;
    private String matricula;
    private boolean telemedicina;
    private boolean presencial;
    private List<CoberturaMedica> coberturaMedicaList;
    private String ubicacion;
    private String honorario;
    @ManyToMany
    private List<Dia> diasTrabajo;
    @OneToOne
    protected Usuario usuario;


}
