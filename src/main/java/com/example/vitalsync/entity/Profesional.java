package com.example.vitalsync.entity;

import com.example.vitalsync.utility.CoberturaMedica;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
public class Profesional extends Persona{
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


}
