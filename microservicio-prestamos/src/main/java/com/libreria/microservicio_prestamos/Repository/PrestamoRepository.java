package com.libreria.microservicio_prestamos.Repository;

import com.libreria.microservicio_prestamos.Model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}
