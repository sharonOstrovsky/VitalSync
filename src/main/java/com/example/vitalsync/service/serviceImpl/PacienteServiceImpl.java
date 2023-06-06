package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.dto.request.paciente.PacienteRequestDTO;
import com.example.vitalsync.dto.request.paciente.PacienteUpdateRequestDTO;
import com.example.vitalsync.dto.request.usuario.UsuarioLoginRequestDTO;
import com.example.vitalsync.dto.response.paciente.PacienteResponseCompletoDTO;
import com.example.vitalsync.dto.response.paciente.PacienteResponseDTO;
import com.example.vitalsync.entity.HistorialMedico;
import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.entity.Turno;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.repository.PacienteRepository;
import com.example.vitalsync.service.service.PacienteService;
import com.example.vitalsync.service.service.ProfesionalService;
import com.example.vitalsync.service.service.TurnoService;
import com.example.vitalsync.utils.Rol;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PacienteServiceImpl implements PacienteService {
    private PacienteRepository pacienteRepository;
    private UsuarioServiceImpl usuarioService;
    private PasswordEncoder passwordEncoder;
    private ProfesionalService profesionalService;
    private TurnoService turnoService;

    //TODO Moddel mapper no va acá
    private final ModelMapper modelMapper = new ModelMapper();

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public List<PacienteResponseCompletoDTO> listarPacientes() {
        List<Paciente> result = pacienteRepository.findAll();
        return result.stream().map(r -> modelMapper.map(r, PacienteResponseCompletoDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PacienteResponseDTO guardarPaciente(PacienteRequestDTO pacienteDto) throws Exception {
        Usuario usuario = modelMapper.map(pacienteDto.getUsuario(), Usuario.class);
        usuario.setClave(passwordEncoder.encode(pacienteDto.getUsuario().getClave()));
        UsuarioLoginRequestDTO usuarioDto = new UsuarioLoginRequestDTO();
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setClave(usuario.getClave());
        Usuario usuarioGuardado = usuarioService.guardarUsuario(usuarioDto);
        usuarioGuardado.setRol(Rol.PACIENTE);

        Paciente paciente = new Paciente();
        paciente.setNombre(pacienteDto.getNombre());
        paciente.setApellido(pacienteDto.getApellido());
        paciente.setEstado(true);
        paciente.setUsuario(usuarioGuardado);
        pacienteRepository.save(paciente);

        return modelMapper.map(paciente, PacienteResponseDTO.class);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) throws Exception {
        return pacienteRepository.save(paciente);
    }
    @Override
    public PacienteResponseDTO obtenerPacientePorId(Long id) throws Exception {

        return modelMapper.map(pacienteRepository.findById(id).orElseThrow(() -> new Exception("Paciente no encontrado")), PacienteResponseDTO.class);
    }

    public PacienteResponseCompletoDTO editarPaciente(Long id, PacienteUpdateRequestDTO paciente) throws Exception {
        Paciente pacienteGuardado = pacienteRepository.findById(id).orElseThrow(() -> new Exception("No se encontró el paciente con el ID: " + id));
        modelMapper.map(paciente, pacienteGuardado);
        pacienteRepository.save(pacienteGuardado);

        return modelMapper.map(pacienteGuardado, PacienteResponseCompletoDTO.class);
    }


    @Override
    public void cambiarEstadoPaciente(Long id) throws Exception {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new Exception("No se encontró ningún paciente con el ID especificado."));
        paciente.setEstado(!paciente.getEstado());
        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Long id) throws Exception {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            pacienteRepository.delete(pacienteOptional.get());
        } else {
            throw new Exception("No se encontró ningún paciente con el ID especificado.");
        }
    }

    @Override
    public Paciente traerPacientePorUsuario(String email) throws Exception {
        Paciente paciente = pacienteRepository.findByUsuarioEmail(email);
        if (paciente == null) {
            throw new Exception("No se encontro ningun paciente con ese email");
        }
        return paciente;
    }
//TODO mover a service turno
    @Override
    public List<Turno> verTurnoProfesional(Long id) throws Exception {

        return profesionalService.mostrarTurnosDisponible(id);
    }

    @Override
    public void reservarTurno (Long id_medico, Long id_turno, Long id_paciente) throws Exception {

        List <Turno> turnos = this.verTurnoProfesional(id_medico);
        Optional<Turno> turnoReservado = turnos.stream()
                .filter(turno -> turno.getId_turno().equals(id_turno))
                .findFirst();
        turnoReservado.get().setDisponible(false);
        turnoService.guardarTurnos(turnos);
        profesionalService.actualizarProfesional(id_medico);
        Optional<Paciente> paciente = pacienteRepository.findById(id_paciente);
        paciente.get().getTurnos().add(turnoReservado.get());
        this.actualizarPaciente(paciente.get());
    }

    @Override
    public List <HistorialMedico> retornarHistorialPorId (Long Id) throws Exception {
        Optional<Paciente> paciente = pacienteRepository.findById(Id);
        List<HistorialMedico> historialMedico = paciente.get().getHistorialMedico();
        return historialMedico;
    }
}
