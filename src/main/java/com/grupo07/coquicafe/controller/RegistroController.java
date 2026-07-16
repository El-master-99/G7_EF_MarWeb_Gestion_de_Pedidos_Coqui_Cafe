package com.grupo07.coquicafe.controller;

import com.grupo07.coquicafe.model.Acceso;
import com.grupo07.coquicafe.model.Usuario;
import com.grupo07.coquicafe.repository.AccesoRepositorio;
import com.grupo07.coquicafe.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private AccesoRepositorio accesoRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String mostrarFormulario() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam String username,
            @RequestParam String password,
            RedirectAttributes redirectAttributes) {

        if (accesoRepo.findByNombreUsuario(username) != null) {
            redirectAttributes.addFlashAttribute("error", "El nombre de usuario ya existe.");
            return "redirect:/registro";
        }

        if (usuarioRepo.findAll().stream().anyMatch(u -> u.getCorreo().equalsIgnoreCase(correo))) {
            redirectAttributes.addFlashAttribute("error", "Ese correo ya está registrado.");
            return "redirect:/registro";
        }

        // Crear Usuario (siempre Activo)
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setRol("Usuario");
        usuario.setFecha_creacion(LocalDate.now());
        usuario.setEstado("Activo");
        usuarioRepo.save(usuario);

        // Crear Acceso (siempre rol USER)
        Acceso acceso = new Acceso();
        acceso.setNombreUsuario(username);
        acceso.setContrasena(passwordEncoder.encode(password));
        acceso.setCorreo(correo);
        acceso.setUsuario(usuario);
        acceso.setRol("USER");
        acceso.setEstado(true);
        accesoRepo.save(acceso);

        redirectAttributes.addFlashAttribute("registrado", true);
        return "redirect:/login?registrado";
    }
}