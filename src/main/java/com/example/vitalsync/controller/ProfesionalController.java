package com.example.vitalsync.controller;

import com.example.vitalsync.entity.Profesional;
import com.example.vitalsync.service.service.ProfesionalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/vitalsync/profesional")
@CrossOrigin("*")
public class ProfesionalController {

    private final ProfesionalService profesionalService;

    @GetMapping(("/{id}"))
    //TODO public ResponseEntity<ProfesionalResponseDTO>
    public ResponseEntity<Profesional> findById(@PathVariable Long id) throws Exception {
        Optional<Profesional> ProfesionalOpt = Optional.ofNullable(profesionalService.obtenerProfesionalPorId(id));

        return ProfesionalOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    //TODO public ResponseEntity<ProfesionalResponseDTO>
    public ResponseEntity<Profesional> create(@RequestBody Profesional profesional) throws Exception {
        Profesional result = profesionalService.guardarProfesional(profesional);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/especialidad")
    public ResponseEntity<List<Profesional>> encontrarPorProfesional(@RequestParam(value = "especialidad", required = false) String especialidad) throws Exception {
        List<Profesional> result = profesionalService.obtenerProfesionalesPorEspecialidad(especialidad);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Profesional>> listarProfesionales() throws Exception{
        List<Profesional> result = profesionalService.listarProfesionales();
        return  ResponseEntity.ok(result);
    }


}
