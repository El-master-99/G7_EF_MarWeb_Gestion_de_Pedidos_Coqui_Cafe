package com.grupo07.coquicafe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo07.coquicafe.model.Acceso;

public interface AccesoRepositorio extends JpaRepository<Acceso, Long> {

    Optional<Acceso> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}