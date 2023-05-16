package com.example.vitalsync.entity;


import lombok.*;

import javax.persistence.*;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String nombre;
    protected String apellido;
    protected String mail;
    protected String telefono;


}
