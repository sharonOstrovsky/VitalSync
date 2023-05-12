package com.example.vitalsync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Persona")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String nombre;
    protected String apellido;
    protected String mail;
    protected String telefono;
    @OneToOne
    protected Usuario usuario;


}
