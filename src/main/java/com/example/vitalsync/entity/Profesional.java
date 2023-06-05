package com.example.vitalsync.entity;

import com.example.vitalsync.utils.CoberturaMedica;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Profesional")
public class Profesional extends Persona{
    private String especialidad;
    private Boolean estado;
    private String matricula;
    private Boolean telemedicina;
    private Boolean presencial;
    @ElementCollection
    private List<Integer> puntuacionList;
    private Integer puntuacion;
    @ElementCollection
    private List<String> comentarios = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "Persona_CoberturaMedica", joinColumns = @JoinColumn(name = "persona_id"))
    @Column(name = "coberturaMedica")
    private List <CoberturaMedica> coberturaMedicaList;
    private String ubicacion;
    private String honorario;
    @OneToMany
    private List <Turno> turnos;
    @ManyToMany
    private List<Dia> diasTrabajo;
    @OneToOne
    protected Usuario usuario;
}
