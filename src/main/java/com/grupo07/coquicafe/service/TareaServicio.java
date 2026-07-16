package com.grupo07.coquicafe.service;

import com.grupo07.coquicafe.model.Estado;
import com.grupo07.coquicafe.model.Tarea;
import java.util.List;

public interface TareaServicio {
    List<Tarea> listarTodas();
    Tarea guardar(Tarea tarea);
    Tarea obtenerPorId(Long id);
    void eliminar(Long id);
    long contarPorEstado(Estado estado);
    Tarea actualizar(Tarea tarea);
}