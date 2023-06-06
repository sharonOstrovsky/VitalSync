package com.example.vitalsync.repository;

import com.example.vitalsync.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long> {
}
