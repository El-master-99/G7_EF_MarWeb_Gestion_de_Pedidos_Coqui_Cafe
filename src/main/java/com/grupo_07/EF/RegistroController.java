package com.grupo_07.EF;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo_07.EF.repositorio.AccesoRepositorio;
import com.grupo_07.EF.repositorio.UsuarioRepositorio;

@Controller
public class RegistroController {

    private final UsuarioRepositorio usuarioRepositorio;
    private final AccesoRepositorio accesoRepositorio;

    public RegistroController(UsuarioRepositorio usuarioRepositorio, AccesoRepositorio accesoRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.accesoRepositorio = accesoRepositorio;
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        if (usuarioRepositorio.existsByCorreo(correo)) {
            model.addAttribute("error", "El correo ya se encuentra registrado.");
            return "registro";
        }

        if (accesoRepositorio.existsByUsername(username)) {
            model.addAttribute("error", "El nombre de usuario ya se encuentra registrado.");
            return "registro";
        }

        if (accesoRepositorio.existsByEmail(correo)) {
            model.addAttribute("error", "El correo ya tiene credenciales de acceso.");
            return "registro";
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setRol("USER");
        usuario.setFechaCreacion(LocalDate.now());
        usuario.setEstado("Activo");

        Usuario usuarioGuardado = usuarioRepositorio.save(usuario);

        Acceso acceso = new Acceso();
        acceso.setUsername(username);
        acceso.setPassword(password);
        acceso.setEmail(correo);
        acceso.setUsuario(usuarioGuardado);

        accesoRepositorio.save(acceso);

        return "redirect:/login?registro";
    }
}
