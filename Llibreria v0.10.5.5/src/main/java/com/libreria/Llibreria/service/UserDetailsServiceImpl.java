package com.libreria.Llibreria.service;

import com.libreria.Llibreria.model.Usuario;
import com.libreria.Llibreria.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=== Intento de autenticación ===");
        System.out.println("Buscando usuario: '" + username + "'");

        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> {
                System.out.println("Usuario NO encontrado: '" + username + "'");
                return new UsernameNotFoundException("Usuario no encontrado: " + username);
            });

        System.out.println("Usuario encontrado en BD:");
        System.out.println("- Username: " + usuario.getUsername());
        System.out.println("- Password: " + usuario.getPassword());
        System.out.println("- Rol: " + usuario.getRol());
        
        // Verificar que el rol no sea nulo o vacío
        if(usuario.getRol() == null || usuario.getRol().trim().isEmpty()) {
            System.err.println("Error: El usuario no tiene rol asignado");
            throw new UsernameNotFoundException("El usuario no tiene rol asignado");
        }

        return new org.springframework.security.core.userdetails.User(
            usuario.getUsername(),
            usuario.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toUpperCase()))
        );
    }
}