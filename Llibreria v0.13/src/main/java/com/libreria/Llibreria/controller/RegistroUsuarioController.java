package com.libreria.Llibreria.controller;

import com.libreria.Llibreria.dto.UsuarioDTO;
import com.libreria.Llibreria.model.Usuario;
import com.libreria.Llibreria.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registro")
public class RegistroUsuarioController {

    private final UsuarioRepository usuarioRepository;

    public RegistroUsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<String> registrarNuevoUsuario(@RequestBody UsuarioDTO dto) {
        if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("El usuario ya existe.");
        }

        String rol = dto.getRol() != null ? dto.getRol().toUpperCase() : "USUARIO";

        Usuario nuevo = new Usuario(
            dto.getUsername(),
            dto.getPassword(),
            dto.getNombre(),
            rol
        );

        usuarioRepository.save(nuevo);
        return ResponseEntity.ok("Usuario registrado correctamente.");
    }
}
