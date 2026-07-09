package com.grupo_07.pc2_thymeleaf;

/*import java.util.Optional;
import jakarta.servlet.http.HttpSession;*/
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
    private final AccesoRepositorio accesoRepositorio;

    //realizamos el cambio para trabajar con spring security y no con el repositorio de acceso
    public thymeleaf(TareaServicio tareaServicio, UsuarioRepositorio usuarioRepositorio, AccesoRepositorio accesoRepositorio) {
        this.tareaServicio = tareaServicio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.accesoRepositorio = accesoRepositorio;
    }

    @GetMapping("/")
    public String redireccionInicial() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @GetMapping("/index")
    public String inicio(Model model, org.springframework.security.core.Authentication authentication) {

        String username = authentication.getName();

        Acceso acceso = accesoRepositorio.findByUsername(username).orElse(null);

        model.addAttribute("pedidos", tareaServicio.listarTodas());
        model.addAttribute("usuarioLogueado", acceso);

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
    public String verPerfil(Model model, org.springframework.security.core.Authentication authentication) {

        String username = authentication.getName();

        Acceso acceso = accesoRepositorio.findByUsername(username).orElse(null);

        model.addAttribute("usuario", acceso);

        return "perfil";
    }

    @GetMapping("/403")
    public String accesoDenegado() {
        return "error/403";
    }
}