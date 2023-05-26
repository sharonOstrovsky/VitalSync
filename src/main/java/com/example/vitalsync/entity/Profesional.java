package com.example.vitalsync.entity;

import com.example.vitalsync.utils.CoberturaMedica;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "Profesional")
public class Profesional extends Persona{
    private String especialidad;
    private boolean estado;
    private String matricula;
    private boolean telemedicina;
    private boolean presencial;
    @ElementCollection
    @CollectionTable(name = "Persona_CoberturaMedica", joinColumns = @JoinColumn(name = "persona_id"))
    @Column(name = "coberturaMedica")
    private List <CoberturaMedica> coberturaMedicaList;
    private String ubicacion;
    private String honorario;
    @ManyToMany
    private List<Dia> diasTrabajo;
    @OneToOne //(cascade =CascadeType.ALL)
    protected Usuario usuario;

    public Profesional() {
    }

}
