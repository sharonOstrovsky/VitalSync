package com.example.vitalsync.dto.response.profesional;

import com.example.vitalsync.utils.CoberturaMedica;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
@Data
public class ProfesionalUpdateResponseDTO {
    private String nombre;
    private String apellido;
    private String telefono;
    private String especialidad;
//    private Boolean estado;
    private String matricula;
    private Boolean telemedicina;
    private Boolean presencial;
    @Enumerated(EnumType.STRING)
    private List<CoberturaMedica> coberturaMedica;
    private String ubicacion;
    private String honorario;
}
