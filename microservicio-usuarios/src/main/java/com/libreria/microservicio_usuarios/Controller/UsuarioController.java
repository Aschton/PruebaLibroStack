package com.libreria.microservicio_usuarios.Controller;

import com.libreria.microservicio_usuarios.DTO.UsuarioDTO;
import com.libreria.microservicio_usuarios.Model.Usuario;
import com.libreria.microservicio_usuarios.Service.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    // GET /api/usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        logger.info("[UsuarioController] GET /api/usuarios");
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    // GET /api/usuarios/1
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        logger.info("[UsuarioController] GET /api/usuarios/{}", id);
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    // POST /api/usuarios
    @PostMapping
    public ResponseEntity<Usuario> crear(@Valid @RequestBody UsuarioDTO dto) {
        logger.info("[UsuarioController] POST /api/usuarios");
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(dto));
    }

    // PUT /api/usuarios/1
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        logger.info("[UsuarioController] PUT /api/usuarios/{}", id);
        return ResponseEntity.ok(usuarioService.actualizar(id, dto));
    }

    // DELETE /api/usuarios/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        logger.info("[UsuarioController] DELETE /api/usuarios/{}", id);
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
