package com.example.vitalsync.dto.response.paciente;

import com.example.vitalsync.entity.Turno;
import com.example.vitalsync.utils.CoberturaMedica;
import lombok.Data;
import java.util.List;

@Data
public class PacienteResponseCompletoDTO {
    private String nombre;
    private String apellido;
    private String telefono;
    private Integer edad;
    private byte[] foto;
    private List<Turno> turnos;
    private CoberturaMedica coberturaMedica;
}
