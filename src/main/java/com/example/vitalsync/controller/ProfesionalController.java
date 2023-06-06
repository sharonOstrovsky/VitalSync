package com.example.vitalsync.controller;


import com.example.vitalsync.dto.request.profesional.ProfesionalComentariosRequestDTO;
import com.example.vitalsync.dto.request.profesional.ProfesionalPuntuacionRequestDTO;
import com.example.vitalsync.dto.request.profesional.ProfesionalRequestDTO;
import com.example.vitalsync.dto.request.profesional.ProfesionalUpdateRequestDTO;
import com.example.vitalsync.dto.response.profesional.*;
import com.example.vitalsync.entity.Profesional;
import com.example.vitalsync.entity.Turno;
import com.example.vitalsync.service.service.ProfesionalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/vitalsync/profesional")
public class ProfesionalController {

    private ProfesionalService profesionalService;


    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody ProfesionalRequestDTO profesional) throws Exception {
        System.out.println(profesional);
        ProfesionalResponseDTO result = profesionalService.guardarProfesional(profesional);
        if(result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ya existente.");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/agregarComentario")
    public ResponseEntity<Profesional> agregarComentario(@RequestBody ProfesionalComentariosRequestDTO profesionalComentariosRequestDTO) throws Exception {
        Profesional result = profesionalService.guardarComentario(profesionalComentariosRequestDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/comentarios/{id}")
    public ResponseEntity<ProfesionalPedirComentariosResponseDTO> listarComentarios(@PathVariable Long id) throws Exception {
        ProfesionalPedirComentariosResponseDTO result = profesionalService.listarComentarios(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/eliminarComentario")
    public ResponseEntity<String> eliminarComentario(@RequestBody ProfesionalComentariosRequestDTO profesionalComentariosRequestDTO) {
        try {
            profesionalService.eliminarComentario(profesionalComentariosRequestDTO);
            return ResponseEntity.ok("Comentario eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping(("/{id}"))
    //TODO public ResponseEntity<ProfesionalResponseDTO>
    public ResponseEntity<Profesional> findById(@PathVariable Long id) throws Exception {
        Optional<Profesional> ProfesionalOpt = Optional.ofNullable(profesionalService.obtenerProfesionalPorId(id));

        return ProfesionalOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/modificarEstado/{id}")
    public ResponseEntity<String> modificarEstadoProfesional(@PathVariable Long id) {
        try {
            profesionalService.actualizarEstadoProfesional(id);
            return ResponseEntity.ok("Estado de profesional cambiado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProfesional(@PathVariable("id") Long id) {
        try {
            profesionalService.eliminarProfesional(id);
            return ResponseEntity.ok("Profesional eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/especialidad")
    public ResponseEntity<List<ProfesionalPorEspecialidadResponseDTO>> encontrarPorProfesional(@RequestParam(value = "especialidad", required = false) String especialidad) throws Exception {
        List<ProfesionalPorEspecialidadResponseDTO> result = profesionalService.obtenerProfesionalesPorEspecialidad(especialidad);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Profesional>> listarProfesionales() throws Exception {
        List<Profesional> result = profesionalService.listarProfesionales();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/editar/{Id}")
    public ResponseEntity<?> modificarProfesional(@PathVariable Long Id, @RequestBody ProfesionalUpdateRequestDTO profesionalDTO) {
        ProfesionalUpdateResponseDTO result;
        try {
            result = profesionalService.editarProfesional(Id, profesionalDTO);
        } catch (HttpMessageNotReadableException e2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e2.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/puntuar/{id}")
    public ResponseEntity<String> puntuarProfesional(@PathVariable Long id, @RequestBody ProfesionalPuntuacionRequestDTO profesionalPuntuacionRequestDTO) throws Exception {
        ProfesionalPuntuacionResponseDTO result;
        try {
            result = profesionalService.puntuarProfesional(id, profesionalPuntuacionRequestDTO);
        } catch (HttpMessageNotReadableException e2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e2.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok("Puntuación registrada.");

    }

    @GetMapping("/puntuacion/{id}")
    public ResponseEntity<ProfesionalPuntuacionResponseDTO> puntuacionDelProfesional(@PathVariable Long id) throws Exception {
        ProfesionalPuntuacionResponseDTO result = profesionalService.obtenerPuntuacion(id);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/usuario/{email}")
    public ResponseEntity<?> encontrarPorUsuario(@PathVariable String email) {
        try {
            Profesional result = profesionalService.traerProfesionalPorUsuario(email);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/turnos/{Id}")
    public ResponseEntity<List<Turno>> verTurnos(@PathVariable Long Id) {
        try {
            Profesional result = profesionalService.obtenerProfesionalPorId(Id);
            return ResponseEntity.ok(result.getTurnos());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
