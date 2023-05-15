package com.example.vitalsync.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Turno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_turno;
    private Date fecha;
    private String observaciones;
    private Long id_paciente;
    private Long id_profesional;
}
