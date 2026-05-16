package com.libreria.microservicio_prestamos.Client;

import com.libreria.microservicio_prestamos.DTO.LibroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Le decimos que este cliente habla con microservicio-libros en el puerto 8081
@FeignClient(name = "microservicio-libros", url = "http://localhost:8081")
public interface LibroClient {

    // Llama a GET http://localhost:8081/api/libros/{id}
    @GetMapping("/api/libros/{id}")
    LibroDTO obtenerLibroPorId(@PathVariable("id") Long id);
}
