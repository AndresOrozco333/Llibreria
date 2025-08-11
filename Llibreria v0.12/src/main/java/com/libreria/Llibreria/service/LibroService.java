package com.libreria.Llibreria.service;

import com.libreria.Llibreria.model.Libro;
import com.libreria.Llibreria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    public Libro buscar(String id) {
        return libroRepository.findById(id).orElse(null);
    }

    public void eliminar(String id) {
        libroRepository.deleteById(id);
    }
}
