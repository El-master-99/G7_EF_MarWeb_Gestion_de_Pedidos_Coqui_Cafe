package com.grupo_07.pc2_thymeleaf;

import java.util.Optional;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.grupo_07.pc2_thymeleaf.servicio.TareaServicio;
import com.grupo_07.pc2_thymeleaf.repositorio.UsuarioRepositorio;
import com.grupo_07.pc2_thymeleaf.repositorio.AccesoRepositorio;

@Controller
public class thymeleaf {

    private final TareaServicio tareaServicio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final AccesoRepositorio accesoRepositorio; // Repositorio de persistencia de credenciales

    public thymeleaf(TareaServicio tareaServicio, UsuarioRepositorio usuarioRepositorio, AccesoRepositorio accesoRepositorio) {
        this.tareaServicio = tareaServicio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.accesoRepositorio = accesoRepositorio;
    }

    @GetMapping("/")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(String usuario, String password, HttpSession session, Model model) {
        Optional<Acceso> usuarioValido = accesoRepositorio.findByUsernameAndPassword(usuario, password);

        if (usuarioValido.isPresent()) {
            session.setAttribute("usuarioLogueado", usuarioValido.get());
            return "redirect:/index"; 
        }
        
        model.addAttribute("error", "Datos de autenticación inválidos.");
        return "login";
    }

    @GetMapping("/index")
    public String inicio(Model model) {
        model.addAttribute("pedidos", tareaServicio.listarTodas());
        return "index";
    }

@GetMapping("/produccion")
    public String produccion(HttpSession session, Model model) {
        // ¡VIDA REAL! Si no hay sesión, rebota directo al login sin mostrar nada
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/";
        }
        
        model.addAttribute("pedidos", tareaServicio.listarTodas());
        model.addAttribute("tarea", new Tarea());
        return "produccion";
    }

    @GetMapping("/usuario")
    public String usuario(HttpSession session, Model model) {
        // ¡VIDA REAL! Si no hay sesión, rebota directo al login
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/";
        }
        
        model.addAttribute("usuarios", usuarioRepositorio.findAll());
        return "usuario";
    }

    @GetMapping("/tarea/editar/{id}")
    public String editarTarea(@PathVariable Long id, Model model) {
        Tarea tarea = tareaServicio.buscarPorId(id);
        model.addAttribute("tarea", tarea);
        model.addAttribute("pedidos", tareaServicio.listarTodas());
        return "produccion";
    }

    @GetMapping("/tarea/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        tareaServicio.eliminar(id);
        return "redirect:/produccion";
    }

    @PostMapping("/tarea/guardar")
    public String guardarTarea(@Valid Tarea tarea, BindingResult resultado, Model model) {
        // Uso de Spring Validator para impedir registros erróneos
        if (resultado.hasErrors()) {
            model.addAttribute("pedidos", tareaServicio.listarTodas());
            return "produccion";
        }
        tareaServicio.guardar(tarea);
        return "redirect:/produccion";
    }
    @GetMapping("/perfil")
public String verPerfil(HttpSession session, Model model) {
    Acceso usuarioLogueado = (Acceso) session.getAttribute("usuarioLogueado");
    
    // Si no ha iniciado sesión, lo mandamos al login
    if (usuarioLogueado == null) {
        return "redirect:/";
    }
    
    // Pasamos el usuario a la vista para mostrar sus datos
    model.addAttribute("usuario", usuarioLogueado);
    return "perfil";
}
}