package com.grupo_07.EF.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo_07.EF.Estado;
import com.grupo_07.EF.Tarea;

public interface TareaRepositorio extends JpaRepository<Tarea, Long> {

        long countByEstado(Estado estado);

}