package com.example.vitalsync.dto.request.profesional;

import com.example.vitalsync.entity.Dia;
import com.example.vitalsync.utils.CoberturaMedica;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
public class ProfesionalUpdateDTO {
    private String nombre;
    private String apellido;
    private String telefono;
    private String especialidad;
    private boolean estado;
    private String matricula;
    private boolean telemedicina;
    private boolean presencial;
    @ElementCollection
    private List<CoberturaMedica> coberturaMedicaList;
    private String ubicacion;
    private String honorario;
    @ManyToMany
    private List<Dia> diasTrabajo;
}
