package com.example.vitalsync.repository;

import com.example.vitalsync.entity.Paciente;
import com.example.vitalsync.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    //Paciente findByEmail(String email);

    @Query("SELECT p FROM Paciente p INNER JOIN p.usuario u WHERE u.email = :email")
    Optional<Paciente> findByEmail(String email);
}

