package com.libreria.microservicio_libros.Repository;

import com.libreria.microservicio_libros.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
