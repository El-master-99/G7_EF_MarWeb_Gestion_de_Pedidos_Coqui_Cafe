package com.grupo_07.pc2_thymeleaf.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo_07.pc2_thymeleaf.Tarea;

public interface TareaRepositorio
extends JpaRepository<Tarea, Long> {

}