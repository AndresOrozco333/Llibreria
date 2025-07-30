package com.libreria.Llibreria.controller;

import com.libreria.Llibreria.model.Libro;
import com.libreria.Llibreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Endpoint completo (útil para debug o backend)
    @GetMapping("/raw")
    public List<Libro> listar() {
        return libroService.listar();
    }

    // Endpoint que usa tu frontend HTML
    @GetMapping
    public List<Map<String, String>> librosParaFrontend() {
        return libroService.listar().stream().map(libro -> {
            String imagenUrl = "/img/default.jpg"; // valor por defecto

            if (libro.getArchivo() != null && libro.getArchivo().getPortada() != null) {
                imagenUrl = "/img/" + libro.getArchivo().getPortada();
            }

            return Map.of(
                "titulo", libro.getTitulo(),
                "descripcion", libro.getDescripcion(),
                "imagenUrl", imagenUrl
            );
        }).collect(Collectors.toList());
    }

    @PostMapping
    public Libro guardar(@RequestBody Libro libro) {
        return libroService.guardar(libro);
    }

    @GetMapping("/{id}")
    public Libro obtener(@PathVariable String id) {
        return libroService.buscar(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        libroService.eliminar(id);
    }
}
