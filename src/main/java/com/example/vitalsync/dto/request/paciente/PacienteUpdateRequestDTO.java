package com.example.vitalsync.dto.request.paciente;
import com.example.vitalsync.utils.CoberturaMedica;
import lombok.Data;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
public class PacienteUpdateRequestDTO {
    private String nombre;
    private String apellido;
//    private Boolean estado;
    private String telefono;
    private int edad;
    private String foto;
    @Enumerated(EnumType.STRING)
    private CoberturaMedica coberturaMedica;
}
