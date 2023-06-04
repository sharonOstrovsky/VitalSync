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
    private Integer edad;
    @Lob
    private byte[] foto;
    @OneToMany
    private List <Turno> turnos;
    @Enumerated (EnumType.STRING)
    private CoberturaMedica coberturaMedica;
    @ElementCollection
    private List<String> observaciones;
    @OneToOne
    protected Usuario usuario;
    private Boolean estado;
}
