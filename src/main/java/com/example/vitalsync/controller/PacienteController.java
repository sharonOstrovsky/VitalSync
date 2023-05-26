package com.example.vitalsync.controller;

import com.example.vitalsync.dto.request.paciente.PacienteRequestDTO;
import com.example.vitalsync.dto.response.PacienteResponseDTO;
import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.service.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/vitalsync/paciente")
@CrossOrigin
public class PacienteController {


    private final PacienteService pacienteService;

    @GetMapping(("/{id}"))
    //TODO public ResponseEntity<ProfesionalResponseDTO>
    public ResponseEntity<Paciente> findById(@PathVariable Long id) throws Exception {
        Optional<Paciente> PacienteOpt = Optional.ofNullable(pacienteService.obtenerPacientePorId(id));

        return PacienteOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    //TODO public ResponseEntity<ProfesionalResponseDTO>
    public ResponseEntity<PacienteResponseDTO> create(@RequestBody PacienteRequestDTO paciente) throws Exception {
        PacienteResponseDTO result = pacienteService.guardarPaciente(paciente);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarPacientes() throws Exception{
        List<Paciente> result = pacienteService.listarPacientes();
        return  ResponseEntity.ok(result);
    }
}
