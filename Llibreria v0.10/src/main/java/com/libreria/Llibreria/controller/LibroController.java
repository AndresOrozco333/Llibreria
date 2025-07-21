package com.libreria.Llibreria.controller;

import com.libreria.Llibreria.model.Libro;
import com.libreria.Llibreria.repository.LibroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroRepository libroRepository;

    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping
    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    @PostMapping
    public Libro guardar(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }
}