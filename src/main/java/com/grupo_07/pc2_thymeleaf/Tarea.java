package com.grupo_07.pc2_thymeleaf;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "Tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Validación: Título no vacío (Exigido en la rúbrica)
    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    private String descripcion;

    // Validación: Fecha futura (Exigido en la rúbrica)
    @Future(message = "La fecha de entrega debe ser una fecha futura")
    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    private String prioridad;

    // Mapeo ORM: Almacena el enumerado como texto legible en SQL Server
    @Enumerated(EnumType.STRING)
    private Estado estado;
}