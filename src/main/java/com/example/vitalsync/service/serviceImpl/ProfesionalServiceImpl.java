package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.entity.Profesional;
import com.example.vitalsync.repository.ProfesionalRepository;
import com.example.vitalsync.service.service.ProfesionalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfesionalServiceImpl implements ProfesionalService {

    private final ProfesionalRepository profesionalRepository;
    @Override
    public List<Profesional> listarProfesionales() throws Exception {
        return profesionalRepository.findAll();
    }

    @Override
    public Profesional guardarProfesional(Profesional profesionalReqDto) throws Exception {
        //TODO Convertir el ProfesionalReqDto a Profesional
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