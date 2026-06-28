package com.grupo_07.EF.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo_07.EF.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    boolean existsByCorreo(String correo);
}