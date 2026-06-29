package com.grupo07.coquicafe.service;

import java.util.List;

import com.grupo07.coquicafe.model.Tarea;
import com.grupo07.coquicafe.model.Estado;

public interface TareaServicio {

    List<Tarea> listarTodas();

    Tarea guardar(Tarea tarea);

    Tarea buscarPorId(Long id);

    void eliminar(Long id);

    long contarPorEstado(Estado estado);

    Tarea cancelar(Long id);
}