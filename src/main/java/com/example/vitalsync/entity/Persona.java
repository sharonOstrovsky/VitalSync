package com.example.vitalsync.entity;

import jakarta.persistence.*;
import lombok.*;

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
