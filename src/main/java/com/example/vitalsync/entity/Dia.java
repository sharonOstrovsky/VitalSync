package com.example.vitalsync.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Dia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_dia;
    private String dia;
    private LocalDate horaEntrada;
    private LocalDate horaSalida;
}
