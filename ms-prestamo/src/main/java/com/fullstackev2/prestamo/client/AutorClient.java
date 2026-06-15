package com.fullstackev2.prestamo.client;

import com.fullstackev2.prestamo.dto.AutorResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Similar a LibroClient pero apunta a ms-autor en puerto 8092
// Cuando llamemos autorClient.obtenerAutorPorId(1),
// Feign hace GET http://localhost:8092/api/autores/1 automáticamente
@FeignClient(name = "ms-autor", url = "http://localhost:8092")
public interface AutorClient {

    @GetMapping("/api/autores/{id}")
    AutorResponseDTO obtenerAutorPorId(@PathVariable Integer id);
}