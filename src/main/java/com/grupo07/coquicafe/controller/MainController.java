package com.grupo07.coquicafe.controller;

import java.security.Principal;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.grupo07.coquicafe.model.Estado;
import com.grupo07.coquicafe.model.Tarea;
import com.grupo07.coquicafe.model.Acceso;
import com.grupo07.coquicafe.repository.AccesoRepositorio;
import com.grupo07.coquicafe.repository.UsuarioRepositorio;
import com.grupo07.coquicafe.service.TareaServicio;

@Controller
public class MainController {

    private final TareaServicio tareaServicio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final AccesoRepositorio accesoRepositorio;

    public MainController(
            TareaServicio tareaServicio,
            UsuarioRepositorio usuarioRepositorio,
            AccesoRepositorio accesoRepositorio) {
        this.tareaServicio = tareaServicio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.accesoRepositorio = accesoRepositorio;
    }

    @GetMapping({ "/", "/login" })
    public String mostrarLogin() {
        return "login";
    }

    @GetMapping("/index")
    public String inicio(Model model, Principal principal) {
        model.addAttribute("pedidos", tareaServicio.listarTodas());

        model.addAttribute("totalPedidos", tareaServicio.listarTodas().size());
        model.addAttribute("pendientes", tareaServicio.contarPorEstado(Estado.PENDIENTE));
        model.addAttribute("enProceso", tareaServicio.contarPorEstado(Estado.EN_PROGRESO));
        model.addAttribute("completadas", tareaServicio.contarPorEstado(Estado.COMPLETADA));
        model.addAttribute("canceladas", tareaServicio.contarPorEstado(Estado.CANCELADA));

        if (principal != null) {
            model.addAttribute("usuarioLogueado", principal.getName());
        }

        return "index";
    }

    @GetMapping("/produccion")
    public String produccion(Model model) {
        model.addAttribute("pedidos", tareaServicio.listarTodas());
        model.addAttribute("tarea", new Tarea());
        return "produccion";
    }

    @GetMapping("/usuario")
    public String usuario(Model model) {
        model.addAttribute("usuarios", usuarioRepositorio.findAll());
        return "usuario";
    }

    @GetMapping("/tarea/editar/{id}")
    public String editarTarea(@PathVariable Long id, Model model) {
        Tarea tarea = tareaServicio.buscarPorId(id);

        if (tarea == null) {
            return "redirect:/produccion?noexiste";
        }

        if (tarea.getEstado() == Estado.COMPLETADA || tarea.getEstado() == Estado.CANCELADA) {
            return "redirect:/produccion?bloqueado";
        }

        model.addAttribute("tarea", tarea);
        model.addAttribute("pedidos", tareaServicio.listarTodas());
        return "produccion";
    }

    @GetMapping("/tarea/cancelar/{id}")
    public String cancelarTarea(@PathVariable Long id) {
        tareaServicio.cancelar(id);
        return "redirect:/produccion?cancelado";
    }

    @PostMapping("/tarea/guardar")
    public String guardarTarea(@Valid Tarea tarea, BindingResult resultado, Model model) {
        if (resultado.hasErrors()) {
            model.addAttribute("pedidos", tareaServicio.listarTodas());
            return "produccion";
        }

        boolean esNuevo = tarea.getId() == null;
        tareaServicio.guardar(tarea);

        return esNuevo ? "redirect:/produccion?guardado" : "redirect:/produccion?actualizado";
    }

    @GetMapping("/perfil")
    public String verPerfil(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }

        Acceso acceso = accesoRepositorio.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("usuario", acceso);

        return "perfil";
    }
}