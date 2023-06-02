package com.example.vitalsync.repository;

import com.example.vitalsync.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.clave = :clave")
    Optional<Usuario> findOneByEmailAndPassword(String email, String clave);
    Usuario findByEmail(String email);
}
