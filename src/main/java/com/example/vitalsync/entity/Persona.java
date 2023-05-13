package com.example.vitalsync.entity;


import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String nombre;
    protected String apellido;
    protected String mail;
    protected String telefono;


}
