package com.grupo_07.EF.servicio;

import java.util.List;

import com.grupo_07.EF.Tarea;

public interface TareaServicio {
    List<Tarea> listarTodas();

    Tarea guardar(Tarea tarea);

    Tarea buscarPorId(Long id);

    void eliminar(Long id);
}