package com.libreria.Llibreria.repository;

import com.libreria.Llibreria.model.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LibroRepository extends MongoRepository<Libro, String> {
    // Puedes agregar métodos personalizados si los necesitas
}
