package com.grupo07.coquicafe.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Acceso")

public class Acceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false, unique = true, length = 50)
    private String nombreUsuario;

    @Column(nullable = false, length = 255)
    private String contrasena;

    @Column(nullable = false, length = 100)
    private String correo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(length = 20)
    private String rol;

    @Column(columnDefinition = "BIT")
    private Boolean estado;
}