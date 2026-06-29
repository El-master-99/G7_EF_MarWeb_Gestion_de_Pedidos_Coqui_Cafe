package com.grupo07.coquicafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo07.coquicafe.model.Tarea;
import com.grupo07.coquicafe.model.Estado;

public interface TareaRepositorio extends JpaRepository<Tarea, Long> {

        long countByEstado(Estado estado);

}