package com.grupo_07.EF.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grupo_07.EF.Estado;
import com.grupo_07.EF.Tarea;
import com.grupo_07.EF.repositorio.TareaRepositorio;

@Service
public class TareaServicioImplementacion implements TareaServicio {

    private final TareaRepositorio repositorio;

    public TareaServicioImplementacion(TareaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Tarea> listarTodas() {
        return repositorio.findAll();
    }

    @Override
    public Tarea guardar(Tarea tarea) {
        return repositorio.save(tarea);
    }

    @Override
    public Tarea buscarPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public long contarPorEstado(Estado estado) {
        return repositorio.countByEstado(estado);
    }

    @Override
    public Tarea cancelar(Long id) {
        Tarea tarea = buscarPorId(id);

        if (tarea == null) {
            return null;
        }

        if (tarea.getEstado() == Estado.COMPLETADA) {
            return tarea;
        }

        tarea.setEstado(Estado.CANCELADA);
        return repositorio.save(tarea);
    }
}