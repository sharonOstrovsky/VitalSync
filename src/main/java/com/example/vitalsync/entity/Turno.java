package com.example.vitalsync.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "Turno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_turno;
    private LocalDate fecha;
    private LocalTime hora;
    private Boolean disponible = true;
    private Boolean concluido= false; // Si el turno paso o todavia esta vigente
    private Long id_paciente;
    private Long id_profesional;

}
