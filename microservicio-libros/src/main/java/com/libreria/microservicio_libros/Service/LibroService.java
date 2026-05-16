package com.libreria.microservicio_libros.Service;

import com.libreria.microservicio_libros.DTO.LibroDTO;
import com.libreria.microservicio_libros.Model.Libro;
import com.libreria.microservicio_libros.Repository.LibroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    // Logger para registrar lo que pasa en el servicio
    private static final Logger logger = LoggerFactory.getLogger(LibroService.class);

    @Autowired
    private LibroRepository libroRepository;

    // Retorna todos los libros de la base de datos
    public List<Libro> obtenerTodos() {
        logger.info("[LibroService] Obteniendo todos los libros");
        return libroRepository.findAll();
    }

    // Busca un libro por id, si no existe lanza un error
    public Libro obtenerPorId(Long id) {
        logger.info("[LibroService] Buscando libro con id={}", id);
        try {
            return libroRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));
        } catch (RuntimeException e) {
            logger.warn("[LibroService] Libro con id={} no encontrado", id);
            throw e;
        }
    }

    // Recibe un DTO, crea el objeto Libro y lo guarda
    public Libro guardar(LibroDTO dto) {
        logger.info("[LibroService] Creando libro: {}", dto.getTitulo());
        try {
            Libro libro = new Libro();
            libro.setTitulo(dto.getTitulo());
            libro.setAutor(dto.getAutor());
            libro.setStock(dto.getStock());
            libro.setEstado(dto.getEstado());
            Libro guardado = libroRepository.save(libro);
            logger.info("[LibroService] Libro creado con id={}", guardado.getId());
            return guardado;
        } catch (Exception e) {
            logger.error("[LibroService] Error al crear libro: {}", e.getMessage());
            throw e;
        }
    }

    // Busca el libro, actualiza sus campos y lo guarda
    public Libro actualizar(Long id, LibroDTO dto) {
        logger.info("[LibroService] Actualizando libro con id={}", id);
        try {
            Libro libro = libroRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));
            libro.setTitulo(dto.getTitulo());
            libro.setAutor(dto.getAutor());
            libro.setStock(dto.getStock());
            libro.setEstado(dto.getEstado());
            Libro actualizado = libroRepository.save(libro);
            logger.info("[LibroService] Libro id={} actualizado", id);
            return actualizado;
        } catch (Exception e) {
            logger.error("[LibroService] Error al actualizar libro id={}: {}", id, e.getMessage());
            throw e;
        }
    }

    // Elimina el libro si existe
    public void eliminar(Long id) {
        logger.info("[LibroService] Eliminando libro con id={}", id);
        try {
            if (!libroRepository.existsById(id)) {
                throw new RuntimeException("Libro no encontrado con id: " + id);
            }
            libroRepository.deleteById(id);
            logger.info("[LibroService] Libro id={} eliminado");
        } catch (Exception e) {
            logger.error("[LibroService] Error al eliminar libro id={}: {}", id, e.getMessage());
            throw e;
        }
    }
}
