package com.grupo_07.EF.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo_07.EF.Acceso;

public interface AccesoRepositorio extends JpaRepository<Acceso, Long> {

    Optional<Acceso> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}