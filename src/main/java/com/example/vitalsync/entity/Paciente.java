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
    @Enumerated (EnumType.STRING)
    private CoberturaMedica coberturaMedica;
    @OneToMany
    private List<HistorialMedico> historialMedico;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    protected Usuario usuario;
    private Boolean estado;
    @OneToMany
    private List<Turno> turnos;
}
