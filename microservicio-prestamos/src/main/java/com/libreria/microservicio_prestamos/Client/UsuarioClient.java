package com.libreria.microservicio_prestamos.Client;

import com.libreria.microservicio_prestamos.DTO.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Le decimos que este cliente habla con microservicio-usuarios en el puerto 8082
@FeignClient(name = "microservicio-usuarios", url = "http://localhost:8082")
public interface UsuarioClient {

    // Llama a GET http://localhost:8082/api/usuarios/{id}
    @GetMapping("/api/usuarios/{id}")
    UsuarioDTO obtenerUsuarioPorId(@PathVariable("id") Long id);
}
