package com.libreria.Llibreria.controller;

import com.libreria.Llibreria.model.Libro;
import com.libreria.Llibreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> listar() {
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
