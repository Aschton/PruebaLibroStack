package com.libreria.microservicio_prestamos.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El id del libro no puede ser nulo")
    private Long libroId;

    @NotNull(message = "El id del usuario no puede ser nulo")
    private Long usuarioId;

    private LocalDate fechaPrestamo;

    @NotBlank(message = "El estado no puede estar vacío")
    @Pattern(regexp = "ACTIVO|DEVUELTO", message = "El estado debe ser ACTIVO o DEVUELTO")
    private String estadoPrestamo; // "ACTIVO" o "DEVUELTO"
}
