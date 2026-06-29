package com.grupo07.coquicafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo07.coquicafe.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    boolean existsByCorreo(String correo);
}