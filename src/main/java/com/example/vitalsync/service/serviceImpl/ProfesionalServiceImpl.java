package com.example.vitalsync.service.serviceImpl;

import com.example.vitalsync.dto.request.profesional.ProfesionalComentariosRequestDTO;
import com.example.vitalsync.dto.request.profesional.ProfesionalPuntuacionRequestDTO;
import com.example.vitalsync.dto.request.profesional.ProfesionalRequestDTO;
import com.example.vitalsync.dto.request.profesional.ProfesionalUpdateRequestDTO;
import com.example.vitalsync.dto.request.usuario.UsuarioLoginRequestDTO;
import com.example.vitalsync.dto.response.profesional.*;
import com.example.vitalsync.entity.Profesional;
import com.example.vitalsync.entity.Turno;
import com.example.vitalsync.entity.Usuario;
import com.example.vitalsync.repository.ProfesionalRepository;
import com.example.vitalsync.repository.TurnoRepository;
import com.example.vitalsync.service.service.ProfesionalService;
import com.example.vitalsync.utils.Rol;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesionalServiceImpl implements ProfesionalService {

    private ProfesionalRepository profesionalRepository;
    private UsuarioServiceImpl usuarioService;
    private PasswordEncoder passwordEncoder;
    private TurnoRepository turnoRepository;

    public ProfesionalServiceImpl(ProfesionalRepository profesionalRepository, UsuarioServiceImpl usuarioService, PasswordEncoder passwordEncoder, TurnoRepository turnoRepository) {
        this.profesionalRepository = profesionalRepository;
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.turnoRepository = turnoRepository;
    }

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<Profesional> listarProfesionales() throws Exception {
        return profesionalRepository.findAll();
    }

    @Override
    public ProfesionalResponseDTO guardarProfesional(ProfesionalRequestDTO profesionalReqDto) throws Exception {
        Usuario usuario = modelMapper.map(profesionalReqDto.getUsuario(), Usuario.class);
        usuario.setClave(passwordEncoder.encode(profesionalReqDto.getUsuario().getClave()));
        UsuarioLoginRequestDTO usuarioDto = new UsuarioLoginRequestDTO();
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setClave(usuario.getClave());
        Usuario usuarioGuardado = usuarioService.guardarUsuario(usuarioDto);
        if(usuarioGuardado == null){
            return null;
        }
        usuarioGuardado.setRol(Rol.PROFESIONAL);

        Profesional profesional = new Profesional();
        profesional.setNombre(profesionalReqDto.getNombre());
        profesional.setApellido(profesionalReqDto.getApellido());
        profesional.setEstado(true);
        profesional.setEspecialidad(profesionalReqDto.getEspecialidad());
        profesional.getTurnos().addAll(this.setearTurnos(profesional.getId()));
        profesional.setUsuario(usuarioGuardado);
        profesionalRepository.save(profesional);
        this.setearIdMedicoTurno(profesional);
        return modelMapper.map(profesional, ProfesionalResponseDTO.class);
    }

    @Override
    public Profesional guardarComentario(ProfesionalComentariosRequestDTO profesionalComentariosRequestDTO) throws Exception {
        Profesional profesional = traerProfesionalPorUsuario(profesionalComentariosRequestDTO.getEmail());
        profesional.getComentarios().add(profesionalComentariosRequestDTO.getComentarios());
        profesionalRepository.save(profesional);
        return profesional;
    }

    @Override
    public ProfesionalPedirComentariosResponseDTO listarComentarios(Long id) throws Exception {

        Profesional profesional = obtenerProfesionalPorId(id);

        return modelMapper.map(profesional, ProfesionalPedirComentariosResponseDTO.class);
    }

    @Override
    public void eliminarComentario(ProfesionalComentariosRequestDTO profesionalComentariosRequestDTO) throws Exception {
        Profesional profesional = traerProfesionalPorUsuario(profesionalComentariosRequestDTO.getEmail());
        List<String> comentarios = profesional.getComentarios();
        List<String> comentariosActualizados = new ArrayList<>();

        for (String coment : comentarios) {
            if (!coment.equals(profesionalComentariosRequestDTO.getComentarios())) {
                comentariosActualizados.add(coment);
            }
        }

        profesional.setComentarios(comentariosActualizados);
        profesionalRepository.save(profesional);

    }

    @Override
    public ProfesionalPuntuacionResponseDTO puntuarProfesional(Long id, ProfesionalPuntuacionRequestDTO profesionalPuntuacionRequestDTO) throws Exception {
        Profesional profesional = obtenerProfesionalPorId(id);
        profesional.getPuntuacionList().add(profesionalPuntuacionRequestDTO.getPuntuacion());

        Integer puntuacion = promedioPuntuacion(profesional.getPuntuacionList());

        profesional.setPuntuacion(puntuacion);

        profesionalRepository.save(profesional);

        return modelMapper.map(puntuacion, ProfesionalPuntuacionResponseDTO.class);
    }

    @Override
    public ProfesionalPuntuacionResponseDTO obtenerPuntuacion(Long id) throws Exception {
        Profesional profesional = obtenerProfesionalPorId(id);
        return modelMapper.map(profesional, ProfesionalPuntuacionResponseDTO.class);
    }


    private Integer promedioPuntuacion(List<Integer> puntuacion) {
        Integer promedio = 0;
        for (Integer p : puntuacion) {
            promedio += p;
        }
        System.out.println("PROMEDIP: " + promedio / puntuacion.size());
        return promedio / puntuacion.size();
    }

    private List<Turno> setearTurnos(Long id) throws Exception {
        List<Turno> turnos = new ArrayList<>();
        LocalDate fechaTurno = LocalDate.now().plus(1, ChronoUnit.WEEKS);
        for (int i = 0; i < 5; i++) {
            if (i != 0) fechaTurno = fechaTurno.plus(1, ChronoUnit.DAYS);
            LocalTime local = LocalTime.of(12, 0, 0);
            for (int j = 0; j < 5; j++) {
                if (j != 0) local = local.plusHours(1);
                Turno turno = new Turno();
                turno.setId_profesional(id);
                turno.setFecha(fechaTurno);
                turno.setHora(local);
                turnoRepository.save(turno);
                turnos.add(turno);
            }
        }

        return turnos;
    }

    private void setearIdMedicoTurno(Profesional profesional) {
        List<Turno> turnos = profesional.getTurnos();
        turnos.forEach(turno -> {
            turno.setId_profesional(profesional.getId());
        });
        profesionalRepository.save(profesional);
    }

    @Override
    public List<Turno> mostrarTurnos(Long id) throws Exception {
        Profesional profesional = this.obtenerProfesionalPorId(id);
        return profesional.getTurnos();
    }

    @Override
    public List<Turno> mostrarTurnosDisponible(Long id) throws Exception {
        Profesional profesional = this.obtenerProfesionalPorId(id);
        List<Turno> turnos = new ArrayList<>();
        return profesional.getTurnos().stream().filter(Turno::getDisponible).collect(Collectors.toList());
    }

    @Override
    public Profesional obtenerProfesionalPorId(Long id) throws Exception {

        return profesionalRepository.findById(id).get();
    }

    @Override
    public void actualizarEstadoProfesional(Long id) throws Exception {
        Profesional profesional = profesionalRepository.findById(id).orElseThrow(() -> new Exception("No se encontró ningún profesional con el ID especificado."));

        boolean estadoActual = profesional.getEstado();
        profesional.setEstado(!estadoActual);

        profesionalRepository.save(profesional);
    }

    @Override
    public List<ProfesionalPorEspecialidadResponseDTO> obtenerProfesionalesPorEspecialidad(String especialidad) throws Exception {
        List<Profesional> profesional = profesionalRepository.buscarPorEspecialidad(especialidad);
        //TODO Hacerlo con model mapper
        return profesional.stream().map(p -> new ProfesionalPorEspecialidadResponseDTO( p.getId(), p.getNombre(), p.getApellido())).collect(Collectors.toList());
    }

    @Override
    public List<ProfesionalPorEspecialidadResponseDTO> obtenerProfesionalesOrdenadosPorPuntuacion() throws Exception {
        List<Profesional> profesionales = profesionalRepository.ordenarDescPorPuntuacion();
        return profesionales.stream().map(p -> new ProfesionalPorEspecialidadResponseDTO(p.getId(), p.getNombre(), p.getApellido())).collect(Collectors.toList());
    }

    @Override
    public List<ProfesionalPorEspecialidadResponseDTO> obtenerProfesionalesOrdenadosPorHonorario() throws Exception {
        List<Profesional> profesionales = profesionalRepository.ordenarAscPorHonorario();
        return profesionales.stream().map(p -> new ProfesionalPorEspecialidadResponseDTO(p.getId(), p.getNombre(), p.getApellido())).collect(Collectors.toList());
    }

    public void eliminarProfesional(Long id) throws Exception {
        Optional<Profesional> profesionalOptional = profesionalRepository.findById(id);
        if (profesionalOptional.isPresent()) {
            profesionalRepository.delete(profesionalOptional.get());
        } else {
            throw new Exception("No se encontró ningún profesional con el ID especificado.");
        }
    }

    public ProfesionalUpdateResponseDTO editarProfesional(Long id, ProfesionalUpdateRequestDTO profesional) throws Exception {
        Profesional profesionalGuardado = profesionalRepository.findById(id).orElseThrow(() -> new Exception("No se encontró el profesional con el ID: " + id));

        modelMapper.map(profesional, profesionalGuardado);

        profesionalRepository.save(profesionalGuardado);

        return modelMapper.map(profesionalGuardado, ProfesionalUpdateResponseDTO.class);
    }

    @Override
    public Profesional traerProfesionalPorUsuario(String email) throws Exception {
        Profesional profesional = profesionalRepository.findByUsuarioEmail(email);
        if (profesional == null) {
            throw new Exception("No se encontro ningun profesional con ese email");
        }
        return profesional;
    }
    @Override
    public void actualizarProfesional(Long Id){
        Optional<Profesional> profesional = profesionalRepository.findById(Id);
        profesionalRepository.save(profesional.get());
    }

    @Override
    public List<Turno> mostrarTurnosReservados(Long id) {
        Optional<Profesional> result = profesionalRepository.findById(id);
        return result.get().getTurnos().stream()
                .filter(turno -> !turno.getDisponible())
                .collect(Collectors.toList());
    }

}