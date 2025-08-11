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

    // Este endpoint es consumido por el HTML (con imagen incluida)
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

    // Endpoint útil para pruebas si quieres obtener todo el objeto
    @GetMapping("/raw")
    public List<Libro> listarCrudo() {
        return libroService.listar();
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
