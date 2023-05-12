package com.example.vitalsync.repositories;

import com.example.vitalsync.entity.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional,Long> {
}
