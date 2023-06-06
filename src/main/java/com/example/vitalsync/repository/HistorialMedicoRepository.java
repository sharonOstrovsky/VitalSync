package com.example.vitalsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface HistorialMedicoRepository extends JpaRepository <com.example.vitalsync.entity.HistorialMedico,Long> {
}
