package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.entity.Profesional;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.repository.ProfesionalRepository;
import com.example.vitalsync.service.service.ProfesionalService;
import com.example.vitalsync.service.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfesionalServiceImpl implements ProfesionalService {
//    public ProfesionalServiceImpl() {
//    }
//
//    public ProfesionalServiceImpl(ProfesionalRepository profesionalRepository, UsuarioService usuarioService) {
//        this.profesionalRepository = profesionalRepository;
//        this.usuarioService = usuarioService;
//    }

    private ProfesionalRepository profesionalRepository;
    private UsuarioService usuarioService;
    @Override
    public List<Profesional> listarProfesionales() throws Exception {
        return profesionalRepository.findAll();
    }

    @Override
    public Profesional guardarProfesional(Profesional profesionalReqDto) throws Exception {
        //TODO Convertir el ProfesionalReqDto a Profesional
        Usuario usuario = usuarioService.guardarUsuario(new Usuario(null,profesionalReqDto.getUsuario().getUsuario(),
                profesionalReqDto.getUsuario().getClave(),profesionalReqDto.getUsuario().getEmail(),profesionalReqDto.getUsuario().getRol()));
        profesionalReqDto.setUsuario(usuario);
        return profesionalRepository.save(profesionalReqDto);
    }

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
        profesionalRepository.deleteById(id);
    }

    @Override
    public List<Profesional> obtenerProfesionalesPorEspecialidad(String especialidad) throws Exception {
        return profesionalRepository.buscarPorEspecialidad(especialidad);
    }
}
