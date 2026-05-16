package com.libreria.microservicio_libros.Controller;

import com.libreria.microservicio_libros.DTO.LibroDTO;
import com.libreria.microservicio_libros.Model.Libro;
import com.libreria.microservicio_libros.Service.LibroService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    // Logger para ver las peticiones que llegan
    private static final Logger logger = LoggerFactory.getLogger(LibroController.class);

    @Autowired
    private LibroService libroService;

    // GET /api/libros - retorna todos los libros
    @GetMapping
    public ResponseEntity<List<Libro>> listar() {
        logger.info("[LibroController] GET /api/libros");
        return ResponseEntity.ok(libroService.obtenerTodos());
    }

    // GET /api/libros/1 - retorna un libro por id
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerPorId(@PathVariable Long id) {
        logger.info("[LibroController] GET /api/libros/{}", id);
        return ResponseEntity.ok(libroService.obtenerPorId(id));
    }

    // POST /api/libros - crea un nuevo libro
    // @Valid activa las validaciones del DTO
    @PostMapping
    public ResponseEntity<Libro> crear(@Valid @RequestBody LibroDTO dto) {
        logger.info("[LibroController] POST /api/libros");
        return ResponseEntity.status(HttpStatus.CREATED).body(libroService.guardar(dto));
    }

    // PUT /api/libros/1 - actualiza un libro existente
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @Valid @RequestBody LibroDTO dto) {
        logger.info("[LibroController] PUT /api/libros/{}", id);
        return ResponseEntity.ok(libroService.actualizar(id, dto));
    }

    // DELETE /api/libros/1 - elimina un libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        logger.info("[LibroController] DELETE /api/libros/{}", id);
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
