package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.dto.request.PacienteRequestDTO;
import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.repository.PacienteRepository;
import com.example.vitalsync.service.service.PacienteService;
import com.example.vitalsync.service.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PacienteServiceImpl implements PacienteService {
    private  PacienteRepository pacienteRepository;
    private UsuarioService usuarioService;
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
    public Paciente guardarPaciente(PacienteRequestDTO pacienteDto) throws Exception {
        Usuario usuario = usuarioService.guardarUsuario(new Usuario(null,pacienteDto.getUsuario().getUsuario(),
                pacienteDto.getUsuario().getClave(),pacienteDto.getUsuario().getEmail(),pacienteDto.getUsuario().getRol()));
        pacienteDto.setUsuario(usuario);
        Paciente p = modelMapper.map(pacienteDto,Paciente.class);
        System.out.println(p);
        return pacienteRepository.save(p);
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
