package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.dto.request.paciente.PacienteRequestDTO;
import com.example.vitalsync.dto.request.profesional.ProfesionalRequestDTO;
import com.example.vitalsync.dto.response.PacienteResponseDTO;
import com.example.vitalsync.dto.response.ProfesionalPorEspecialidadResponseDTO;
import com.example.vitalsync.dto.response.ProfesionalResponseDTO;
import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.entity.Profesional;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.repository.ProfesionalRepository;
import com.example.vitalsync.repository.UsuarioRepository;
import com.example.vitalsync.service.service.ProfesionalService;
import com.example.vitalsync.service.service.UsuarioService;
import com.example.vitalsync.utils.Rol;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfesionalServiceImpl implements ProfesionalService {

    private ProfesionalRepository profesionalRepository;
    private UsuarioServiceImpl usuarioService;
    private UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<Profesional> listarProfesionales() throws Exception {
        return profesionalRepository.findAll();
    }

    @Override
    public ProfesionalResponseDTO guardarProfesional(ProfesionalRequestDTO profesionalReqDto) throws Exception {
        Usuario usuario = modelMapper.map(profesionalReqDto.getUsuario(),Usuario.class);
        usuarioRepository.save(usuario);
        usuario.setRol(Rol.PROFESIONAL);
        Profesional profesional = new Profesional();
        profesional.setNombre(profesionalReqDto.getNombre());
        profesional.setApellido(profesionalReqDto.getApellido());
        profesional.setUsuario(usuario);
        profesional.setEstado(true);
        profesional.setEspecialidad(profesionalReqDto.getEspecialidad());
        profesionalRepository.save(profesional);
        return modelMapper.map(profesional, ProfesionalResponseDTO.class);
    }

//    @Override
//    public PacienteResponseDTO guardarPaciente(PacienteRequestDTO pacienteDto) throws Exception {
////        usuarioService.guardarUsuario(pacienteDto.getUsuario());
//        Usuario usuario = modelMapper.map(pacienteDto.getUsuario(), Usuario.class);
//        usuarioRepository.save(usuario); //TODO Cambiar a service
////        usuarioService.guardarUsuario(usuario);
//        usuario.setRol(Rol.PACIENTE);
//        System.out.println(usuario);
//        Paciente paciente = new Paciente();
//        paciente.setNombre(pacienteDto.getNombre());
//        paciente.setApellido(pacienteDto.getApellido());
//        paciente.setUsuario(usuario);
//        System.out.println(paciente);
//        pacienteRepository.save(paciente);
//        return modelMapper.map(paciente, PacienteResponseDTO.class);
//    }
    @Override
    public Profesional obtenerProfesionalPorId(Long id) throws Exception {
        return profesionalRepository.findById(id).get();
    }

    @Override
    public Profesional actualizarProfesional(Profesional profesional) throws Exception {
        return null;
    }

    @Override
    public void eliminarProfesional(Long id) throws Exception {
        Profesional profesional = profesionalRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontró ningún profesional con el ID especificado."));
        profesional.setEstado(false);
        profesionalRepository.save(profesional);
    }

    @Override
    public List<ProfesionalPorEspecialidadResponseDTO> obtenerProfesionalesPorEspecialidad(String especialidad) throws Exception {
        List<Profesional> profesional = profesionalRepository.buscarPorEspecialidad(especialidad);
        //TODO Hacerlo con model mapper
        return profesional.stream()
                .map(p -> new ProfesionalPorEspecialidadResponseDTO(p.getNombre(), p.getApellido()))
                .collect(Collectors.toList());
    }

}
