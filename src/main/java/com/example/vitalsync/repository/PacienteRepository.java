package com.example.vitalsync.repository;

import com.example.vitalsync.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Paciente p INNER JOIN p.usuario u WHERE u.email = :email")
    Paciente findByUsuarioEmail(@Param("email") String email);

}
