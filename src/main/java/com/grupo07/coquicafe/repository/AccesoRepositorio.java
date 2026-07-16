package com.grupo07.coquicafe.repository;

import com.grupo07.coquicafe.model.Acceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccesoRepositorio extends JpaRepository<Acceso, Long> {
    Acceso findByNombreUsuario(String nombreUsuario);
    Acceso findByUsuario_Id(Long usuarioId);
}