package com.libreria.microservicio_prestamos.Controller;

import com.libreria.microservicio_prestamos.DTO.PrestamoDTO;
import com.libreria.microservicio_prestamos.Model.Prestamo;
import com.libreria.microservicio_prestamos.Service.PrestamoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private static final Logger logger = LoggerFactory.getLogger(PrestamoController.class);

    @Autowired
    private PrestamoService prestamoService;

    // GET /api/prestamos
    @GetMapping
    public ResponseEntity<List<Prestamo>> listar() {
        logger.info("[PrestamoController] GET /api/prestamos");
        return ResponseEntity.ok(prestamoService.obtenerTodos());
    }

    // GET /api/prestamos/1
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerPorId(@PathVariable Long id) {
        logger.info("[PrestamoController] GET /api/prestamos/{}", id);
        return ResponseEntity.ok(prestamoService.obtenerPorId(id));
    }

    // POST /api/prestamos
    @PostMapping
    public ResponseEntity<Prestamo> crear(@Valid @RequestBody PrestamoDTO dto) {
        logger.info("[PrestamoController] POST /api/prestamos");
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoService.crear(dto));
    }

    // PUT /api/prestamos/1
    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizar(@PathVariable Long id, @Valid @RequestBody PrestamoDTO dto) {
        logger.info("[PrestamoController] PUT /api/prestamos/{}", id);
        return ResponseEntity.ok(prestamoService.actualizar(id, dto));
    }

    // DELETE /api/prestamos/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        logger.info("[PrestamoController] DELETE /api/prestamos/{}", id);
        prestamoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
