package com.grupo_07.pc2_thymeleaf;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String rol;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    private String estado;
}
