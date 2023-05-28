package com.example.vitalsync.controller;

import com.example.vitalsync.dto.request.profesional.ProfesionalRequestDTO;
import com.example.vitalsync.dto.response.profesional.ProfesionalPorEspecialidadResponseDTO;
import com.example.vitalsync.dto.response.profesional.ProfesionalResponseDTO;
import com.example.vitalsync.entity.Profesional;
import com.example.vitalsync.service.service.ProfesionalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/vitalsync/profesional")
public class ProfesionalController {

    private ProfesionalService profesionalService;

    @GetMapping(("/{id}"))
    //TODO public ResponseEntity<ProfesionalResponseDTO>
    public ResponseEntity<Profesional> findById(@PathVariable Long id) throws Exception {
        Optional<Profesional> ProfesionalOpt = Optional.ofNullable(profesionalService.obtenerProfesionalPorId(id));

        return ProfesionalOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("modificarEstado/{id}")
    public ResponseEntity<String> modificarEstadoProfesional(@PathVariable Long id) {
        try {
            profesionalService.actualizarEstadoProfesional(id);
            return ResponseEntity.ok("Estado de profesional cambiado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarProfesional(@PathVariable("id") Long id) {
        try {
            profesionalService.eliminarProfesional(id);
            return ResponseEntity.ok("Profesional eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún profesional con el ID especificado.");
        }
    }
    @PostMapping("/crear")
    //TODO public ResponseEntity<ProfesionalResponseDTO>
    public ResponseEntity<ProfesionalResponseDTO> create(@RequestBody ProfesionalRequestDTO profesional) throws Exception {
        ProfesionalResponseDTO result = profesionalService.guardarProfesional(profesional);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/especialidad")
    public ResponseEntity<List<ProfesionalPorEspecialidadResponseDTO>> encontrarPorProfesional(@RequestParam(value = "especialidad", required = false) String especialidad) throws Exception {
        List<ProfesionalPorEspecialidadResponseDTO> result = profesionalService.obtenerProfesionalesPorEspecialidad(especialidad);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Profesional>> listarProfesionales() throws Exception{
        List<Profesional> result = profesionalService.listarProfesionales();
        return  ResponseEntity.ok(result);
    }

}
