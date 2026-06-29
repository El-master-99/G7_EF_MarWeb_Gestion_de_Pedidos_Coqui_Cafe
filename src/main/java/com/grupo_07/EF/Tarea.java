package com.grupo_07.EF;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "Tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    private String descripcion;

    @NotNull(message = "La fecha de entrega es obligatoria")
    @FutureOrPresent(message = "La fecha de entrega debe ser hoy o una fecha futura")
    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @NotBlank(message = "La prioridad es obligatoria")
    private String prioridad;

    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.PENDIENTE;
}