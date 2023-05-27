package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.dto.request.UsuarioLoginRequestDTO;
import com.example.vitalsync.dto.request.paciente.PacienteRequestDTO;
import com.example.vitalsync.dto.response.PacienteResponseDTO;
import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.repository.PacienteRepository;
import com.example.vitalsync.repository.UsuarioRepository;
import com.example.vitalsync.service.service.PacienteService;
import com.example.vitalsync.service.service.UsuarioService;
import com.example.vitalsync.utils.Rol;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PacienteServiceImpl implements PacienteService {
    private  PacienteRepository pacienteRepository;
    private UsuarioServiceImpl usuarioService;
    private PasswordEncoder passwordEncoder;

    //TODO Moddel mapper no va acá
    private final ModelMapper modelMapper = new ModelMapper();

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Override
    public List<Paciente> listarPacientes() throws Exception {
        return pacienteRepository.findAll();
    }

    @Override
    public PacienteResponseDTO guardarPaciente(PacienteRequestDTO pacienteDto) throws Exception {
        Usuario usuario = modelMapper.map(pacienteDto.getUsuario(), Usuario.class);
        usuario.setClave(passwordEncoder.encode(pacienteDto.getUsuario().getClave()));
        UsuarioLoginRequestDTO usuarioDto = new UsuarioLoginRequestDTO();
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setClave(usuario.getClave());
        Usuario usuarioGuardado= usuarioService.guardarUsuario(usuarioDto);
        usuarioGuardado.setRol(Rol.PACIENTE);

        Paciente paciente = new Paciente();
        paciente.setNombre(pacienteDto.getNombre());
        paciente.setApellido(pacienteDto.getApellido());
        paciente.setUsuario(usuarioGuardado);
        pacienteRepository.save(paciente);

        return modelMapper.map(paciente, PacienteResponseDTO.class);
    }

    @Override
    public Paciente obtenerPacientePorId(Long id) throws Exception {
        return pacienteRepository.findById(id).get();
    }

    @Override
    public Paciente actualizarPersonal(Paciente paciente) throws Exception {
        return pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Long id) throws Exception {
        pacienteRepository.deleteById(id);
    }
}
