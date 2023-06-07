package com.example.vitalsync.controller;

import com.example.vitalsync.dto.request.historial.HistorialRequestDTO;
import com.example.vitalsync.dto.request.paciente.PacienteRequestDTO;
import com.example.vitalsync.dto.request.paciente.PacienteUpdateRequestDTO;

import com.example.vitalsync.dto.response.historial.HistorialResponseDTO;
import com.example.vitalsync.dto.response.paciente.PacienteResponseCompletoDTO;
import com.example.vitalsync.dto.response.paciente.PacienteResponseDTO;
import com.example.vitalsync.entity.HistorialMedico;
import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.entity.Turno;
import com.example.vitalsync.service.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/vitalsync/paciente")
@AllArgsConstructor
@CrossOrigin
public class PacienteController {
    private final PacienteService pacienteService;

    @GetMapping(("/{id}"))
    public ResponseEntity<PacienteResponseDTO> findById(@PathVariable Long id) throws Exception {
        Optional<PacienteResponseDTO> pacienteOpt = Optional.ofNullable(pacienteService.obtenerPacientePorId(id));

        return pacienteOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody PacienteRequestDTO paciente) throws Exception {
        PacienteResponseDTO result = pacienteService.guardarPaciente(paciente);
        if(result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ya existente.");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteResponseCompletoDTO>> listarPacientes() throws Exception {
        List<PacienteResponseCompletoDTO> result = pacienteService.listarPacientes();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/usuario/{email}")
    public ResponseEntity<?> encontrarPorUsuario(@PathVariable String email) {
        try {
            Paciente result = pacienteService.traerPacientePorUsuario(email);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/editar/{Id}")
    public ResponseEntity<?> modificarPaciente(@PathVariable Long Id, @RequestBody PacienteUpdateRequestDTO pacienteDTO) {
        PacienteResponseCompletoDTO result;
        try {
            result = pacienteService.editarPaciente(Id, pacienteDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/modificarEstado/{id}")
    public ResponseEntity<?> modificarEstadoPaciente(@PathVariable Long id) {
        try {
            pacienteService.cambiarEstadoPaciente(id);
            return ResponseEntity.ok("Estado de profesional cambiado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable("id") Long id) {
        try {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Paciente eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/reservar-turno")
    public ResponseEntity<String> reservarTurno(
            @RequestParam("id_medico") Long id_medico,
            @RequestParam("id_turno") Long id_turno,
            @RequestParam("id_paciente") Long id_paciente
    ) {
        try {
            pacienteService.reservarTurno(id_medico, id_turno, id_paciente);
            return ResponseEntity.ok("Turno reservado exitosamente");
        } catch (Exception e) {
            // Manejar el error adecuadamente y devolver una respuesta de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al reservar turno");
        }
    }

    @GetMapping("/historial/{id}")
    public ResponseEntity<List<HistorialMedico>> devolverHistorial(@PathVariable("id") Long Id) {
        try {
            List<HistorialMedico> result = pacienteService.retornarHistorialPorId(Id);
//            return ResponseEntity.ok("HistorialMedicoEnviado");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/agregarHistorial/{id}")
    public ResponseEntity<HistorialResponseDTO> agregarHistorial(@PathVariable ("id") Long Id, @RequestBody HistorialRequestDTO historialRequestDTO) {
        try {
            pacienteService.agregarAlHistorial(Id, historialRequestDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/turnosPacienteId/{Id}")
    public ResponseEntity<List<Turno>> turnosIdPaciente(@PathVariable("Id") Long id){
        List <Turno> result;
        try {
            result = pacienteService.listarTurnosPorId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(result);
    }
}