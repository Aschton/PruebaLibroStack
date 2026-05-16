package com.libreria.microservicio_prestamos.Service;

import com.libreria.microservicio_prestamos.Client.LibroClient;
import com.libreria.microservicio_prestamos.Client.UsuarioClient;
import com.libreria.microservicio_prestamos.DTO.LibroDTO;
import com.libreria.microservicio_prestamos.DTO.PrestamoDTO;
import com.libreria.microservicio_prestamos.DTO.UsuarioDTO;
import com.libreria.microservicio_prestamos.Model.Prestamo;
import com.libreria.microservicio_prestamos.Repository.PrestamoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {

    private static final Logger logger = LoggerFactory.getLogger(PrestamoService.class);

    @Autowired
    private PrestamoRepository prestamoRepository;

    // Feign Clients para comunicarse con los otros microservicios
    @Autowired
    private LibroClient libroClient;

    @Autowired
    private UsuarioClient usuarioClient;

    public List<Prestamo> obtenerTodos() {
        logger.info("[PrestamoService] Obteniendo todos los prestamos");
        return prestamoRepository.findAll();
    }

    public Prestamo obtenerPorId(Long id) {
        logger.info("[PrestamoService] Buscando prestamo con id={}", id);
        try {
            return prestamoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Prestamo no encontrado con id: " + id));
        } catch (RuntimeException e) {
            logger.warn("[PrestamoService] Prestamo con id={} no encontrado", id);
            throw e;
        }
    }

    public Prestamo crear(PrestamoDTO dto) {
        logger.info("[PrestamoService] Creando prestamo para libroId={} y usuarioId={}", dto.getLibroId(), dto.getUsuarioId());
        try {
            // Verificar que el libro existe llamando a microservicio-libros via Feign
            LibroDTO libro = libroClient.obtenerLibroPorId(dto.getLibroId());
            if (libro == null) {
                throw new RuntimeException("El libro con id " + dto.getLibroId() + " no existe");
            }
            logger.info("[PrestamoService] Libro encontrado: {}", libro.getTitulo());

            // Verificar que el usuario existe llamando a microservicio-usuarios via Feign
            UsuarioDTO usuario = usuarioClient.obtenerUsuarioPorId(dto.getUsuarioId());
            if (usuario == null) {
                throw new RuntimeException("El usuario con id " + dto.getUsuarioId() + " no existe");
            }
            logger.info("[PrestamoService] Usuario encontrado: {}", usuario.getNombre());

            // Crear el préstamo
            Prestamo prestamo = new Prestamo();
            prestamo.setLibroId(dto.getLibroId());
            prestamo.setUsuarioId(dto.getUsuarioId());
            prestamo.setFechaPrestamo(LocalDate.now());
            prestamo.setEstadoPrestamo(dto.getEstadoPrestamo());

            Prestamo guardado = prestamoRepository.save(prestamo);
            logger.info("[PrestamoService] Prestamo creado con id={}", guardado.getId());
            return guardado;

        } catch (Exception e) {
            logger.error("[PrestamoService] Error al crear prestamo: {}", e.getMessage());
            throw e;
        }
    }

    public Prestamo actualizar(Long id, PrestamoDTO dto) {
        logger.info("[PrestamoService] Actualizando prestamo con id={}", id);
        try {
            Prestamo prestamo = prestamoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Prestamo no encontrado con id: " + id));
            prestamo.setLibroId(dto.getLibroId());
            prestamo.setUsuarioId(dto.getUsuarioId());
            prestamo.setEstadoPrestamo(dto.getEstadoPrestamo());
            Prestamo actualizado = prestamoRepository.save(prestamo);
            logger.info("[PrestamoService] Prestamo id={} actualizado", id);
            return actualizado;
        } catch (Exception e) {
            logger.error("[PrestamoService] Error al actualizar prestamo id={}: {}", id, e.getMessage());
            throw e;
        }
    }

    public void eliminar(Long id) {
        logger.info("[PrestamoService] Eliminando prestamo con id={}", id);
        try {
            if (!prestamoRepository.existsById(id)) {
                throw new RuntimeException("Prestamo no encontrado con id: " + id);
            }
            prestamoRepository.deleteById(id);
            logger.info("[PrestamoService] Prestamo id={} eliminado", id);
        } catch (Exception e) {
            logger.error("[PrestamoService] Error al eliminar prestamo id={}: {}", id, e.getMessage());
            throw e;
        }
    }
}
