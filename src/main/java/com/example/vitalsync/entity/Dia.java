package com.example.vitalsync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Float horaEntrada;
    private Float horaSalida;
}
