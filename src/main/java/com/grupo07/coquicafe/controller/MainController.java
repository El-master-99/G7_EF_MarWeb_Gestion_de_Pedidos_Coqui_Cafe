package com.grupo07.coquicafe.controller;

import com.grupo07.coquicafe.model.Estado;
import com.grupo07.coquicafe.model.Tarea;
import com.grupo07.coquicafe.service.TareaServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @Autowired
    private TareaServicio tareaServicio;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pedidos", tareaServicio.listarTodas());

        model.addAttribute("pendientes",
                tareaServicio.contarPorEstado(Estado.PENDIENTE));

        model.addAttribute("enProceso",
                tareaServicio.contarPorEstado(Estado.EN_PROGRESO));

        model.addAttribute("completadas",
                tareaServicio.contarPorEstado(Estado.COMPLETADA));

        return "index";
    }
@GetMapping("/tasks")
public String listarTareas(Model model) {
    model.addAttribute("tareas", tareaServicio.listarTodas());
    model.addAttribute("tareaNueva", new Tarea()); // importante
    if (!model.containsAttribute("tarea")) {
        model.addAttribute("tarea", null);
    }
    return "produccion";
}

@GetMapping("/tasks/edit/{id}")
public String editarTarea(@PathVariable Long id, Model model) {
    Tarea tarea = tareaServicio.obtenerPorId(id);
    if (tarea == null) {
        return "redirect:/tasks";
    }
    model.addAttribute("tarea", tarea);
    model.addAttribute("tareas", tareaServicio.listarTodas());
    return "produccion";
}

@PostMapping("/tasks/save")
public String guardarTarea(@Valid @ModelAttribute("tareaNueva") Tarea tarea, BindingResult result, Model model, RedirectAttributes ra) {
    if (result.hasErrors()) {
        model.addAttribute("tareas", tareaServicio.listarTodas());
        model.addAttribute("tareaNueva", tarea);
        model.addAttribute("error", "Corrige los errores en el formulario");
        return "produccion";
    }
    tareaServicio.guardar(tarea);
    ra.addFlashAttribute("success", "Tarea guardada correctamente");
    return "redirect:/tasks";
}

@PostMapping("/tasks/update")
public String actualizarTarea(@Valid @ModelAttribute("tarea") Tarea tarea, BindingResult result, Model model, RedirectAttributes ra) {
    if (result.hasErrors()) {
        model.addAttribute("tareas", tareaServicio.listarTodas());
        model.addAttribute("tarea", tarea);
        model.addAttribute("error", "Corrige los errores en el formulario");
        return "produccion";
    }
    tareaServicio.actualizar(tarea);
    ra.addFlashAttribute("success", "Tarea actualizada correctamente");
    return "redirect:/tasks";
}
    @GetMapping("/tasks/delete/{id}")
    public String eliminarTarea(@PathVariable Long id, RedirectAttributes ra) {
        tareaServicio.eliminar(id);
        ra.addFlashAttribute("success", "Tarea eliminada correctamente");
        return "redirect:/tasks";
    }

    @GetMapping("/error/403")
    public String error403() {
        return "error/403";
    }
}