package com.libreria.microservicio_libros.Service;

import com.libreria.microservicio_libros.Model.Libro;
import com.libreria.microservicio_libros.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public Libro obtenerPorId(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    public Libro actualizar(Long id, Libro libro) {
        libro.setId(id);
        return libroRepository.save(libro);
    }

    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }
}
