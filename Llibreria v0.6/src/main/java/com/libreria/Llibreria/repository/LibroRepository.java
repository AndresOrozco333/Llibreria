package com.libreria.Llibreria.repository;

import com.libreria.Llibreria.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Aquí  definir métodos personalizados
}
