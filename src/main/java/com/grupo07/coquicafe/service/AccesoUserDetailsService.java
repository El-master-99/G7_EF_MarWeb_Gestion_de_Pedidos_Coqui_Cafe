package com.grupo07.coquicafe.service;

import com.grupo07.coquicafe.model.Acceso;
import com.grupo07.coquicafe.repository.AccesoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccesoUserDetailsService implements UserDetailsService {

    @Autowired
    private AccesoRepositorio accesoRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Acceso acceso = accesoRepo.findByNombreUsuario(username);
        
        if (acceso == null) {
            throw new UsernameNotFoundException("Usuario o contraseña incorrectos.");
        }
        if (acceso.getEstado() != null && !acceso.getEstado()) {
            throw new DisabledException("La cuenta está inactiva.");
        }

        return User.builder()
                .username(acceso.getNombreUsuario())
                .password(acceso.getContrasena())
                .roles(acceso.getRol() != null ? acceso.getRol() : "USER")
                .build();
    }
}