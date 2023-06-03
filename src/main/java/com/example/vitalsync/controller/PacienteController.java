package com.example.vitalsync.controller;

import com.example.vitalsync.dto.request.paciente.PacienteRequestDTO;
import com.example.vitalsync.dto.request.usuario.UsuarioLoginRequestDTO;
import com.example.vitalsync.dto.response.paciente.PacienteResponseDTO;
import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.service.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/vitalsync/paciente")
@AllArgsConstructor
@CrossOrigin
public class PacienteController {
    private final PacienteService pacienteService;


//    //TODO public ResponseEntity<ProfesionalResponseDTO>
//    public ResponseEntity<Paciente> findById(@PathVariable Long id) throws Exception {
//        Optional<Paciente> PacienteOpt = Optional.ofNullable(pacienteService.obtenerPacientePorId(id));
//
//        return PacienteOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
@GetMapping(("/{id}"))
    public ResponseEntity<Paciente> findById(@PathVariable Long id) throws Exception {
        Optional<Paciente> pacienteOpt = Optional.ofNullable(pacienteService.obtenerPacientePorId(id));

        return pacienteOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/crear")
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

    @GetMapping("/usuario/{email}")
    public ResponseEntity<Paciente> encontrarPorUsuario(@PathVariable String email) {
        try {
           Paciente result = pacienteService.traerPacientePorUsuario(email);
           return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



    }
