package com.grupo07.coquicafe.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.grupo07.coquicafe.model.Acceso;
import com.grupo07.coquicafe.repository.AccesoRepositorio;

@Service
public class AccesoDetailsService implements UserDetailsService {

    private final AccesoRepositorio accesoRepositorio;

    public AccesoDetailsService(AccesoRepositorio accesoRepositorio) {
        this.accesoRepositorio = accesoRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Acceso acceso = accesoRepositorio.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        String cargo = acceso.getUsuario().getRol();
        String rolSeguridad = obtenerRolSeguridad(cargo);

        return User.builder()
                .username(acceso.getUsername())
                .password("{noop}" + acceso.getPassword())
                .roles(rolSeguridad)
                .build();
    }

    private String obtenerRolSeguridad(String cargo) {
        if (cargo == null) {
            return "USER";
        }

        String cargoNormalizado = cargo.toLowerCase();

        if (cargoNormalizado.contains("gerente") || cargoNormalizado.contains("administradora")) {
            return "ADMIN";
        }

        return "USER";
    }
}