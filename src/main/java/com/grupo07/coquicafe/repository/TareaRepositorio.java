package com.grupo07.coquicafe.repository;

import com.grupo07.coquicafe.model.Estado;
import com.grupo07.coquicafe.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepositorio extends JpaRepository<Tarea, Long> {
    // Método para contar tareas dinámicamente en el Dashboard (index)
    long countByEstado(Estado estado);
}