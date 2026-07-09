package com.grupo_07.pc2_thymeleaf.servicio;

import com.grupo_07.pc2_thymeleaf.Acceso;
import com.grupo_07.pc2_thymeleaf.repositorio.AccesoRepositorio;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccesoUserDetailsService implements UserDetailsService {

    private final AccesoRepositorio accesoRepositorio;

    public AccesoUserDetailsService(AccesoRepositorio accesoRepositorio) {
        this.accesoRepositorio = accesoRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Acceso acceso = accesoRepositorio.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new User(
                acceso.getUsername(),
                acceso.getPassword(),
                acceso.getEstado(),
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority("ROLE_" + acceso.getRol()))
        );
    }
}