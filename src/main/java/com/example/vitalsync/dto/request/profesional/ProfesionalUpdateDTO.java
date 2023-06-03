package com.example.vitalsync.dto.request.profesional;

import com.example.vitalsync.entity.Dia;
import com.example.vitalsync.utils.CoberturaMedica;
import lombok.Data;

import java.util.List;
@Data
public class ProfesionalUpdateDTO {
    private String nombre;
    private String apellido;
    private String telefono;
    private String especialidad;
    private Boolean estado;
    private String matricula;
    private Boolean telemedicina;
    private Boolean presencial;
    private List<CoberturaMedica> coberturaMedicaList;
    private String ubicacion;
    private String honorario;
    private List<Dia> diasTrabajo;
}
