package com.grupo07.coquicafe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Correo inválido")
    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @NotBlank(message = "El cargo no puede estar vacío")
    @Column(nullable = false, length = 50)
    private String rol;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fecha_creacion;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false, length = 20)
    private String estado;
}