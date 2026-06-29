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

    @Column(name = "nombre_usuario", nullable = false, unique = true)
    private String username;

    @Column(name = "contrasena", nullable = false)
    private String password;

    @Column(name = "correo", nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}