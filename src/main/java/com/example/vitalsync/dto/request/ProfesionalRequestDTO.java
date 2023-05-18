package com.example.vitalsync.dto.request;

import com.example.vitalsync.entity.Dia;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.utils.CoberturaMedica;
import lombok.Data;

import java.util.List;

@Data
public class ProfesionalRequestDTO {
    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;
    private String especialidad;
    private boolean estado;
    private String matricula;
    private boolean telemedicina;
    private boolean presencial;
    private List<CoberturaMedica> coberturaMedicaList;
    private String ubicacion;
    private String honorario;
    private List<Dia> diasTrabajo;
    protected Usuario usuario;
}
