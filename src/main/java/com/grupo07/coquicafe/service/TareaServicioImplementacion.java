package com.grupo07.coquicafe.service;

import com.grupo07.coquicafe.model.Estado;
import com.grupo07.coquicafe.model.Tarea;
import com.grupo07.coquicafe.repository.TareaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TareaServicioImplementacion implements TareaServicio {

    @Autowired
    private TareaRepositorio repositorio;

    @Override
    public List<Tarea> listarTodas() {
        return repositorio.findAll();
    }

    @SuppressWarnings("null")
    @Override
    @Transactional
    public Tarea guardar(Tarea tarea) {
        return repositorio.save(tarea);
    }

    @SuppressWarnings("null")
    @Override
    public Tarea obtenerPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    @Override
    @Transactional
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public long contarPorEstado(Estado estado) {
        return repositorio.countByEstado(estado);
    }

    @SuppressWarnings("null")
    @Override
    @Transactional
    public Tarea actualizar(Tarea tarea) {
        return repositorio.save(tarea);
    }
}