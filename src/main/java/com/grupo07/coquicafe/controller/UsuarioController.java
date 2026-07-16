package com.grupo07.coquicafe.controller;

import com.grupo07.coquicafe.model.Acceso;
import com.grupo07.coquicafe.model.Usuario;
import com.grupo07.coquicafe.repository.AccesoRepositorio;
import com.grupo07.coquicafe.repository.UsuarioRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private AccesoRepositorio accesoRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private void cargarModeloUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        Map<Long, Acceso> accesoPorUsuario = new HashMap<>();
        for (Acceso a : accesoRepositorio.findAll()) {
            if (a.getUsuario() != null) {
                accesoPorUsuario.put(a.getUsuario().getId(), a);
            }
        }
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("accesoPorUsuario", accesoPorUsuario);
    }

    @GetMapping("/admin/usuarios")
    public String listarUsuarios(Model model) {
        cargarModeloUsuarios(model);
        model.addAttribute("usuarioNuevo", new Usuario());
        return "usuario";
    }

    @PostMapping("/admin/usuarios/save")
    public String guardarUsuario(@Valid @ModelAttribute("usuarioNuevo") Usuario usuario,
                                  BindingResult result,
                                  @RequestParam("nombreUsuario") String nombreUsuario,
                                  @RequestParam("contrasena") String contrasena,
                                  @RequestParam("rolAcceso") String rolAcceso,
                                  Model model, RedirectAttributes ra) {

        if (accesoRepositorio.findByNombreUsuario(nombreUsuario) != null) {
            cargarModeloUsuarios(model);
            model.addAttribute("usuarioNuevo", usuario);
            model.addAttribute("error", "Ese nombre de usuario de acceso ya existe.");
            return "usuario";
        }

        if (result.hasErrors()) {
            cargarModeloUsuarios(model);
            model.addAttribute("usuarioNuevo", usuario);
            model.addAttribute("error", "Corrige los errores en el formulario.");
            return "usuario";
        }

        usuario.setFecha_creacion(LocalDate.now());
        usuarioRepositorio.save(usuario);

        Acceso acceso = new Acceso();
        acceso.setNombreUsuario(nombreUsuario);
        acceso.setContrasena(passwordEncoder.encode(contrasena));
        acceso.setCorreo(usuario.getCorreo());
        acceso.setUsuario(usuario);
        acceso.setRol(rolAcceso);
        acceso.setEstado("Activo".equalsIgnoreCase(usuario.getEstado()));
        accesoRepositorio.save(acceso);

        ra.addFlashAttribute("success", "Usuario creado correctamente.");
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/admin/usuarios/update")
    public String actualizarUsuario(@Valid @ModelAttribute("usuario") Usuario usuarioForm,
                                     BindingResult result,
                                     @RequestParam("rolAcceso") String rolAcceso,
                                     @RequestParam(value = "contrasena", required = false) String contrasena,
                                     Model model, RedirectAttributes ra) {

        Usuario usuarioExistente = usuarioRepositorio.findById(usuarioForm.getId()).orElse(null);
        if (usuarioExistente == null) {
            ra.addFlashAttribute("error", "El usuario no existe.");
            return "redirect:/admin/usuarios";
        }

        if (result.hasErrors()) {
            cargarModeloUsuarios(model);
            model.addAttribute("error", "Corrige los errores en el formulario.");
            return "usuario";
        }

        usuarioExistente.setNombre(usuarioForm.getNombre());
        usuarioExistente.setCorreo(usuarioForm.getCorreo());
        usuarioExistente.setRol(usuarioForm.getRol());
        usuarioExistente.setEstado(usuarioForm.getEstado());
        usuarioRepositorio.save(usuarioExistente);

        Acceso acceso = accesoRepositorio.findByUsuario_Id(usuarioExistente.getId());
        if (acceso != null) {
            acceso.setCorreo(usuarioForm.getCorreo());
            acceso.setRol(rolAcceso);
            acceso.setEstado("Activo".equalsIgnoreCase(usuarioForm.getEstado()));
            if (contrasena != null && !contrasena.isBlank()) {
                acceso.setContrasena(passwordEncoder.encode(contrasena));
            }
            accesoRepositorio.save(acceso);
        }

        ra.addFlashAttribute("success", "Usuario actualizado correctamente.");
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/admin/usuarios/delete/{id}")
    public String eliminarUsuario(@PathVariable Long id, RedirectAttributes ra) {
        Acceso acceso = accesoRepositorio.findByUsuario_Id(id);
        if (acceso != null) {
            accesoRepositorio.delete(acceso);
        }
        usuarioRepositorio.deleteById(id);
        ra.addFlashAttribute("success", "Usuario eliminado correctamente.");
        return "redirect:/admin/usuarios";
    }
}