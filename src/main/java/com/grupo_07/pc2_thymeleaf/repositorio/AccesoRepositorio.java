package com.grupo_07.pc2_thymeleaf.repositorio;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo_07.pc2_thymeleaf.Acceso;

public interface AccesoRepositorio extends JpaRepository<Acceso, Long> {

    Optional<Acceso> findByUsername(String username);
}