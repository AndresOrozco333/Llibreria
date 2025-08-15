package com.libreria.Llibreria.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UsuarioInfoController {

    @GetMapping("/user-role")
    public Map<String, String> obtenerRol(Authentication authentication) {
        String rol = authentication.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .map(r -> r.replace("ROLE_", ""))
            .findFirst()
            .orElse("INVITADO");

        return Map.of("rol", rol);
    }
}
