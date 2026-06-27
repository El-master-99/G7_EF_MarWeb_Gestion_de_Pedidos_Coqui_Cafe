package com.grupo_07.pc2_thymeleaf;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Acceso") 
public class Acceso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Vincula el atributo técnico 'username' requerido a tu columna 'nombre_usuario'
    @Column(name = "nombre_usuario") 
    private String username;

    // Vincula el atributo técnico 'password' a tu columna 'contrasena'
    @Column(name = "contrasena") 
    private String password;

    // Vincula el atributo técnico 'email' a tu columna 'correo'
    @Column(name = "correo")     
    private String email;
}