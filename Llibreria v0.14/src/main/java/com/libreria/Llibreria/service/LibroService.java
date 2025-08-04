package com.libreria.Llibreria.service;

import com.libreria.Llibreria.model.Libro;
import com.libreria.Llibreria.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    public Libro buscar(String id) {
        Optional<Libro> resultado = libroRepository.findById(id);
        return resultado.orElse(null);
    }

    public void eliminar(String id) {
        libroRepository.deleteById(id);
    }
}
