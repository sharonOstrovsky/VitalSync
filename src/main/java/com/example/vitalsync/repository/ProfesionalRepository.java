package com.example.vitalsync.repository;

import com.example.vitalsync.entity.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional,Long> {
    @Query (value = "SELECT DISTINCT p FROM Profesional p " +
    "WHERE (:especialidad IS NULL OR p.especialidad LIKE CONCAT('%',:especialidad, '%')AND p.estado IS TRUE)")
    List <Profesional> buscarPorEspecialidad (@Param("especialidad") String especialidad);

    @Query("SELECT p FROM Profesional p INNER JOIN p.usuario u WHERE u.email = :email")
    Profesional findByUsuarioEmail(@Param("email") String email);

    @Query(value = "SELECT p FROM Profesional p ORDER BY  puntuacion DESC")
    List<Profesional> ordenarDescPorPuntuacion();

    @Query(value = "SELECT p FROM Profesional p ORDER BY  honorario ASC")
    List<Profesional> ordenarAscPorHonorario();

}
