package com.grupo_07.pc2_thymeleaf.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grupo_07.pc2_thymeleaf.Tarea;
import com.grupo_07.pc2_thymeleaf.repositorio.TareaRepositorio;

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
}