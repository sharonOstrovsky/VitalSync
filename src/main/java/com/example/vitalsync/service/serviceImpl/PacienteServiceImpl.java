package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.dto.request.paciente.PacienteUpdateRequestDTO;
import com.example.vitalsync.dto.request.usuario.UsuarioLoginRequestDTO;
import com.example.vitalsync.dto.request.paciente.PacienteRequestDTO;
import com.example.vitalsync.dto.response.paciente.PacienteResponseCompletoDTO;
import com.example.vitalsync.dto.response.paciente.PacienteResponseDTO;
import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.repository.PacienteRepository;
import com.example.vitalsync.service.service.PacienteService;
import com.example.vitalsync.utils.Rol;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.lang.model.element.PackageElement;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PacienteServiceImpl implements PacienteService {
    private PacienteRepository pacienteRepository;
    private UsuarioServiceImpl usuarioService;
    private PasswordEncoder passwordEncoder;

    //TODO Moddel mapper no va acá
    private final ModelMapper modelMapper = new ModelMapper();

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public List<PacienteResponseCompletoDTO> listarPacientes() {
        List<Paciente> result =pacienteRepository.findAll();
        return result.stream().map(r -> modelMapper.map(r,PacienteResponseCompletoDTO.class)).collect(Collectors.toList());
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
    public void cambiarEstadoPaciente (Long id) throws Exception {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new Exception("No se encontró ningún paciente con el ID especificado."));
        paciente.setEstado(!paciente.getEstado());
        pacienteRepository.save(paciente);
    }
    @Override
    public void eliminarPaciente (Long id) throws Exception {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if(pacienteOptional.isPresent()){
            pacienteRepository.delete(pacienteOptional.get());
        }else {
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
}
